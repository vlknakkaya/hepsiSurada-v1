package com.hepsisurada.orderservice.model.dto;

import java.util.List;

public class OrderDTO {

	private long id;
	private List<Long> productIdList;
	private long userId;
	private long stateId;

	public OrderDTO() {
		super();
	}

	public OrderDTO(long id, List<Long> productIdList, long userId, long stateId) {
		super();
		this.id = id;
		this.productIdList = productIdList;
		this.userId = userId;
		this.stateId = stateId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Long> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		this.productIdList = productIdList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	@Override
	public String toString() {
		return "OrderDTO [id=" + id + ", productIdList=" + productIdList + ", userId=" + userId + ", stateId=" + stateId
				+ "]";
	}

}
