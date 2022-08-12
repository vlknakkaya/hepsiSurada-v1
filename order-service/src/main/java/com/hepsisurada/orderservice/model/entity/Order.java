package com.hepsisurada.orderservice.model.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ElementCollection
	private List<Long> productIdList;
	
	private long userId;
	
	@OneToOne
	@JoinColumn(name = "state_id", referencedColumnName = "id")
	private OrderState state;

	public Order() {
		super();
	}

	public Order(List<Long> productIdList, long userId, OrderState state) {
		super();
		this.productIdList = productIdList;
		this.userId = userId;
		this.state = state;
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

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", productIdList=" + productIdList + ", userId=" + userId + ", state=" + state + "]";
	}

}
