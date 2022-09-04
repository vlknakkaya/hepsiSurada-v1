package com.hepsisurada.productservice.util;

public class EventType {
	
	private EventType() {
	}

	public static final int GENERAL = 0;
	
	public static final int USER_CREATED = 1;
	public static final int USER_UPDATED = 2;
	public static final int USER_REMOVED = 3;
	public static final int PRODUCT_TYPE_CREATED = 4;
	public static final int PRODUCT_TYPE_UPDATED = 5;
	public static final int PRODUCT_TYPE_REMOVED = 6;
	public static final int PRODUCT_CREATED = 7;
	public static final int PRODUCT_UPDATED = 8;
	public static final int PRODUCT_REMOVED = 9;
	
}
