package com.example.demo.event.streamprocessing.serde;

import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;

import com.google.gson.Gson;

public class JsonDeserializer<T> implements Deserializer<T> {
	
	Gson gson = new Gson();
	
	private Class<T> deserializerClass;
	
	public JsonDeserializer(Class<T> deserializerClass) {
		this.deserializerClass = deserializerClass;
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		if (Objects.isNull(data)) {
			return null;
		}
		
		return gson.fromJson(new String(data), deserializerClass);
	}
	
	@Override
	public void close() {
		
	}
}
