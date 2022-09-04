package com.hepsisurada.userservice.util;

public class EventType {
	
	private EventType() {
	}

	public static final int GENERAL = 0;
	
	// Email event types
	public static final int USER_CREATED = 1;
	public static final int USER_UPDATED = 2;
	public static final int USER_REMOVED = 3;
	public static final int PRODUCT_CREATED = 4;
	
}
