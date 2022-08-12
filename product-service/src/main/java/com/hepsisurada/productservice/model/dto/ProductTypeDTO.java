package com.hepsisurada.productservice.model.dto;

public class ProductTypeDTO {

	private long id;
	private long topTypeId;
	private String name;
	
	public ProductTypeDTO() {
	}

	public ProductTypeDTO(long id, long topTypeId, String name) {
		this.id = id;
		this.topTypeId = topTypeId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTopTypeId() {
		return topTypeId;
	}

	public void setTopTypeId(long topTypeId) {
		this.topTypeId = topTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductTypeDTO [id=" + id + ", topTypeId=" + topTypeId + ", name=" + name + "]";
	}
	
}
