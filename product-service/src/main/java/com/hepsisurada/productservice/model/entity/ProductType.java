package com.hepsisurada.productservice.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private long topTypeId;
	
	private String name;
	
	public ProductType() {
		super();
	}

	public ProductType(long topTypeId, String name) {
		super();
		this.topTypeId = topTypeId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		return "ProductType [id=" + id + ", topTypeId=" + topTypeId + ", name=" + name + "]";
	}
	
}
