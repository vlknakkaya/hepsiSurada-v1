package com.hepsisurada.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepsisurada.emailservice.util.EmailEvent;

@Component
public class KafkaConsumer {

    private final ObjectMapper objectMapper;

    @Autowired
	public KafkaConsumer(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}
	
    @KafkaListener(topics = "email-topic")
    public void consumeMessage(String message) {
    	try {
			EmailEvent event = objectMapper.readValue(message, EmailEvent.class);
			
			// TODO: make your own business
			System.out.println("<KAFKA CONSUMER> Event consumed -> " + event);
		} catch (JacksonException e) {
			e.printStackTrace();
		}
    }
    
}
