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
import com.hepsisurada.orderservice.util.EmailEvent;
import com.hepsisurada.orderservice.util.EventType;

@Service
public class OrderService {

	private OrderRepository repository;
	private KafkaProducer producer;

	@Autowired
	public OrderService(OrderRepository repository, KafkaProducer producer) {
		this.repository = repository;
		this.producer = producer;
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
	public Order save(Order entity) {
		boolean isCreated = entity.getId() == null;
		
		Order order = repository.save(entity);
		
		if (isCreated) {
			producer.send(new EmailEvent(EventType.ORDER_CREATED, "admin@hepsisurada", "New order was created: " + order));
		} else {
			producer.send(new EmailEvent(EventType.ORDER_UPDATED, "admin@hepsisurada", "Order was updated: " + order));
		}
		
		return order;
	}

	@Log
	@Performance
	public void remove(Order entity) {
		producer.send(new EmailEvent(EventType.ORDER_REMOVED, "admin@hepsisurada", "Order was removed: " + entity));
		
		repository.delete(entity);
	}

}
