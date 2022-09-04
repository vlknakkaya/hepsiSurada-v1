package com.hepsisurada.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.orderservice.aspect.annotation.Log;
import com.hepsisurada.orderservice.aspect.annotation.Performance;
import com.hepsisurada.orderservice.exception.EntityNotFoundException;
import com.hepsisurada.orderservice.model.entity.OrderState;
import com.hepsisurada.orderservice.repository.OrderStateRepository;
import com.hepsisurada.orderservice.util.EmailEvent;
import com.hepsisurada.orderservice.util.EventType;

@Service
public class OrderStateService {

	private OrderStateRepository repository;
	private KafkaProducer producer;

	@Autowired
	public OrderStateService(OrderStateRepository repository, KafkaProducer producer) {
		this.repository = repository;
		this.producer = producer;
	}

	@Log
	@Performance
	public OrderState findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("OrderState", "id", id));
	}

	@Log
	@Performance
	public List<OrderState> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public OrderState save(OrderState entity) {
		boolean isCreated = entity.getId() == null;
		
		OrderState orderState = repository.save(entity);
		
		if (isCreated) {
			producer.send(new EmailEvent(EventType.ORDER_STATE_CREATED, "admin@hepsisurada", "New order state was created: " + orderState));
		} else {
			producer.send(new EmailEvent(EventType.ORDER_STATE_UPDATED, "admin@hepsisurada", "Order state was updated: " + orderState));
		}
		
		return orderState;
	}

	@Log
	@Performance
	public void remove(OrderState entity) {
		producer.send(new EmailEvent(EventType.ORDER_STATE_REMOVED, "admin@hepsisurada", "Order state was removed: " + entity));
		
		repository.delete(entity);
	}
	
}
