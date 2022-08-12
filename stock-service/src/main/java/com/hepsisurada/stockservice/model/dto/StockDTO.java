package com.hepsisurada.stockservice.model.dto;

public class StockDTO {

	private long id;
	private long productId;
	private long count;
	
	public StockDTO() {
		super();
	}

	public StockDTO(long id, long productId, long count) {
		super();
		this.id = id;
		this.productId = productId;
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StockDTO [id=" + id + ", productId=" + productId + ", count=" + count + "]";
	}
	
}
