package com.hepsisurada.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hepsisurada.orderservice.model.entity.Order;
import com.hepsisurada.orderservice.model.entity.OrderState;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(long userId);
	
	List<Order> findByProductIdList(long id);
	
	List<Order> findByState(OrderState state);
	
}
