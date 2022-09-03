package com.hepsisurada.emailservice.util;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class KafkaEvent {

	private String id = UUID.randomUUID().toString();

	private LocalDateTime date = LocalDateTime.now();
	
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

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
