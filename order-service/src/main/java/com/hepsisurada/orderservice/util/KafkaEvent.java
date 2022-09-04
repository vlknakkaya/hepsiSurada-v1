package com.hepsisurada.orderservice.util;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class KafkaEvent {

	private String id = UUID.randomUUID().toString();

	private LocalDateTime date = LocalDateTime.now();
	
	private int eventType = EventType.GENERAL;
	
	@JsonIgnore
	private String topic = "general-topic";

	protected KafkaEvent() {
		super();
	}

	protected KafkaEvent(String topic) {
		super();
		this.topic = topic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
