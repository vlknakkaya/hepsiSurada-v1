package com.hepsisurada.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.orderservice.exception.EntityNotFoundException;
import com.hepsisurada.orderservice.model.entity.OrderState;
import com.hepsisurada.orderservice.repository.OrderStateRepository;

@Service
public class OrderStateService {

	private OrderStateRepository repository;

	@Autowired
	public OrderStateService(OrderStateRepository repository) {
		super();
		this.repository = repository;
	}
	
	public OrderState findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderState", "id", id));
	}
	
	public List<OrderState> findAll() {
		return repository.findAll();
	}
	
	public OrderState save(OrderState orderState) {
		return repository.save(orderState);
	}
	
	public void removeById(long id) {
		repository.deleteById(id);
	}
	
}
