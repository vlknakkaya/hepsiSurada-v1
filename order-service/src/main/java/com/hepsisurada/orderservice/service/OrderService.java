package com.hepsisurada.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.orderservice.aspect.annotation.Log;
import com.hepsisurada.orderservice.aspect.annotation.Performance;
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

	@Log
	@Performance
	public Order findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order", "id", id));
	}

	@Log
	@Performance
	public List<Order> findByUserId(long userId) {
		return repository.findByUserId(userId);
	}

	@Log
	@Performance
	public List<Order> findByProductIdList(long id) {
		return repository.findByProductIdList(id);
	}

	@Log
	@Performance
	public List<Order> findByState(OrderState state) {
		return repository.findByState(state);
	}

	@Log
	@Performance
	public List<Order> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public Order save(Order order) {
		return repository.save(order);
	}

	@Log
	@Performance
	public void removeById(long id) {
		repository.deleteById(id);
	}

}
