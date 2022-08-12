package com.hepsisurada.orderservice.model.dto;

public class OrderStateDTO {
	
	private long id;
	private String name;
	
	public OrderStateDTO() {
		super();
	}

	public OrderStateDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "OrderStateDTO [id=" + id + ", name=" + name + "]";
	}

}
