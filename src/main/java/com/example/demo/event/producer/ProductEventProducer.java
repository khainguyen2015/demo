package com.example.demo.event.producer;

import java.util.Objects;
import java.util.Properties;

import javax.annotation.ManagedBean;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.event.constant.Constant;
import com.example.demo.event.model.Search;
import com.example.demo.event.model.UserViewProduct;
import com.example.demo.model.ProductModel;
import com.google.gson.Gson;

@ManagedBean
public class ProductEventProducer {
	
	private static Logger LOG = LoggerFactory.getLogger(ProductEventProducer.class);
	
	private KafkaProducer<String, String> producer;
	
	private Properties properties = new Properties();
	
	public ProductEventProducer() {
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constant.KAFKA_BOOTSTRAP_SERVER);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		// create safe Producer
		properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, Integer.valueOf(Integer.MAX_VALUE));
		properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
		
		producer = new KafkaProducer<>(properties);
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Shutting down {}...", this.getClass().getName());
			LOG.info("Closing kafka producer...");
			producer.close();
			LOG.info("DONE");
		}));
		
	}
	
	public void writeSearchEvent(String userId, String searchTerm) {
		if (Objects.isNull(userId) || Objects.isNull(searchTerm)) {
			throw new IllegalArgumentException();
		}
		
		Gson gson = new Gson();
		Search search = new Search(userId, searchTerm);
		
		ProducerRecord<String, String> record = new ProducerRecord<>(Constant.KAFKA_USER_SEARCH_TOPIC_NAME, search.getUserID(), gson.toJson(search));
		
		writeRecord(record);
	}
	
	public void writeClickEvent(String userId, ProductModel productClicked) {
		if (Objects.isNull(userId) || Objects.isNull(productClicked)) {
			throw new IllegalArgumentException();
		}
		
		Gson gson = new Gson();
		UserViewProduct userViewProduct = new UserViewProduct();
		userViewProduct.setUserId(userId);
		userViewProduct.setProductId(productClicked.getId());
		userViewProduct.setProductName(productClicked.getProductName());

		ProducerRecord<String, String> record = new ProducerRecord<>(Constant.KAFKA_USER_CLICK_TOPIC_NAME, userViewProduct.getUserId(), gson.toJson(userViewProduct));
		
		writeRecord(record);
	}
	
	private void writeRecord(ProducerRecord<String, String> record) {
		
        producer.send(record, (RecordMetadata r, Exception e) -> {
            if (e != null) {
                System.out.println("Error producing to topic " + r.topic());
                e.printStackTrace();
            }
        });
		
		producer.flush();
	}
}
