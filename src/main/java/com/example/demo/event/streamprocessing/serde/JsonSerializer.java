package com.example.demo.event.streamprocessing.serde;

import org.apache.kafka.common.serialization.Serializer;

import com.google.gson.Gson;

public class JsonSerializer<T> implements Serializer<T> {
	
	Gson gson = new Gson();
	
	@Override
	public byte[] serialize(String topic, T data) {
		return gson.toJson(data).getBytes();
	}
	
	@Override
	public void close() {
		
	}

}
