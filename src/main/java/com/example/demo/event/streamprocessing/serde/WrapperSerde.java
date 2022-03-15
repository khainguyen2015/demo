package com.example.demo.event.streamprocessing.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class WrapperSerde<T> implements Serde<T> {
	
	private Deserializer<T> deserializer;
	
	private Serializer<T> serializer;
	
	public WrapperSerde(Class<T> serdeClass) {
		deserializer = new JsonDeserializer<>(serdeClass);
		serializer = new JsonSerializer<>();
	}

	@Override
	public Serializer<T> serializer() {
		return serializer;
	}

	@Override
	public Deserializer<T> deserializer() {
		// TODO Auto-generated method stub
		return deserializer;
	}

}
