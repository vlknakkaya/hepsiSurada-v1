package com.hepsisurada.productservice.model.dto;

public class ProductDTO {
	
	private long id;
	private long typeId;
	private String name;
	private double price;
	
	public ProductDTO() {
	}

	public ProductDTO(long id, long typeId, String name, double price) {
		this.id = id;
		this.typeId = typeId;
		this.name = name;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", typeId=" + typeId + ", name=" + name + ", price=" + price + "]";
	}

}
