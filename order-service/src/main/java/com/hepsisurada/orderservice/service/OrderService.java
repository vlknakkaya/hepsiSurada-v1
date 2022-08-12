package com.hepsisurada.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.orderservice.exception.EntityNotFoundException;
import com.hepsisurada.orderservice.model.entity.Order;
import com.hepsisurada.orderservice.model.entity.OrderState;
import com.hepsisurada.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private OrderRepository repository;

	@Autowired
	public OrderService(OrderRepository repository) {
		super();
		this.repository = repository;
	}

	public Order findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order", "id", id));
	}

	public List<Order> findByUserId(long userId) {
		return repository.findByUserId(userId);
	}

	public List<Order> findByProductIdList(long id) {
		return repository.findByProductIdList(id);
	}

	public List<Order> findByState(OrderState state) {
		return repository.findByState(state);
	}

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order save(Order order) {
		return repository.save(order);
	}

	public void removeById(long id) {
		repository.deleteById(id);
	}

}
