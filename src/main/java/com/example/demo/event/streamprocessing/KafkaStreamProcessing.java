package com.example.demo.event.streamprocessing;

import java.time.Duration;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.ManagedBean;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.event.constant.Constant;
import com.example.demo.event.model.Search;
import com.example.demo.event.model.UserActivity;
import com.example.demo.event.model.UserViewProduct;
import com.example.demo.event.streamprocessing.serde.WrapperSerde;

@ManagedBean
public class KafkaStreamProcessing implements StreamProcessingService {
	
	private static Logger LOG = LoggerFactory.getLogger(KafkaStreamProcessing.class);
	
	private StreamsBuilder builder;
	
	private KafkaStreams streams;
	
	private Properties properties = new Properties();

	public KafkaStreamProcessing() {
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "user_activities_tracker");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		init();
	}
	
	@Override
	public void stopStreamProcessing() {
		streams.close();
	}

	@Override
	public void startStreamProcessing() {
		streams.start();
	}

	private void init() {
		initStreamTopology();
		
		streams = new KafkaStreams(builder.build(), properties);
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			LOG.info("Shutting down {}...", this.getClass().getName());
			LOG.info("Closing kafka stream...");
			streams.close();
			LOG.info("DONE");
		}));
	}
	
	private void initStreamTopology() {
		builder = new StreamsBuilder();
		
		KStream<String, Search> searches = builder.stream(Constant.KAFKA_USER_SEARCH_TOPIC_NAME,
				Consumed.with(Serdes.String(), new WrapperSerde<Search>(Search.class)));
		
		KStream<String, UserViewProduct> views = builder.stream(Constant.KAFKA_USER_CLICK_TOPIC_NAME,
				Consumed.with(Serdes.String(), new WrapperSerde<UserViewProduct>(UserViewProduct.class)));

		final ValueJoiner<Search, UserViewProduct, UserActivity> joiner = (userSearched, userViewed) -> {
			UserActivity userActivity = new UserActivity();
			
			if (Objects.nonNull(userSearched)) {
				userActivity.setSearchTerm(userSearched.getSearchTerms());
				userActivity.setUserId(userSearched.getUserID());
			}
			
			if (Objects.nonNull(userViewed)) {
				userActivity.setProductViewedId(userViewed.getProductId());
				userActivity.setProductViewedName(userViewed.getProductName());
			}
			
			return userActivity;
		};
		
		StreamJoined<String, Search, UserViewProduct> streamJoinConfig = StreamJoined.with(Serdes.String(),
				new WrapperSerde<Search>(Search.class),
				new WrapperSerde<UserViewProduct>(UserViewProduct.class));
		
		KStream<String, UserActivity> userActivities = searches.leftJoin(
			views,
			joiner,
			JoinWindows.of(Duration.ofMinutes(1)).before(Duration.ZERO),
			streamJoinConfig
		);
		
		userActivities.to(Constant.KAFKA_USER_ACTIVITY_TOPIC_NAME,
			Produced.with(Serdes.String(),
			new WrapperSerde<UserActivity>(UserActivity.class))
		);
	}
}
