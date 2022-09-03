package com.hepsisurada.emailservice.util;

public class EmailEvent extends KafkaEvent {

	private static final String TOPIC_NAME = "email-topic";
	
	private String to;

	private String message;

	public EmailEvent() {
		super(TOPIC_NAME);
	}

	public EmailEvent(String to, String message) {
		super(TOPIC_NAME);
		this.to = to;
		this.message = message;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "EmailEvent [id=" + getId() + ", date=" + getDate() + ", to=" + to + ", message=" + message + "]";
	}

}
