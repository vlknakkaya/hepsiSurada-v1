package com.hepsisurada.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsisurada.orderservice.aspect.annotation.Performance;
import com.hepsisurada.orderservice.aspect.annotation.Log;
import com.hepsisurada.orderservice.util.KafkaEvent;

@Component
public class KafkaProducer {
	
	private final ObjectMapper objectMapper;
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	public KafkaProducer(ObjectMapper objectMapper, KafkaTemplate<String, String> kafkaTemplate) {
		this.objectMapper = objectMapper;
		this.kafkaTemplate = kafkaTemplate;
	}

	@Log
	@Performance
	public void send(KafkaEvent event) {
		try {
			String message = objectMapper.writeValueAsString(event);
			kafkaTemplate.send(event.getTopic(), message);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
