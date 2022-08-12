package com.hepsisurada.stockservice.exception;

public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7812284382632748315L;
	
	private final String message;

	public EntityNotFoundException(String entityName, String fieldName, Object value) {
		message = String.format("%s not found with %s = %s", entityName, fieldName, value);
	}

	@Override
	public String getMessage() {
		return message;
	}

}