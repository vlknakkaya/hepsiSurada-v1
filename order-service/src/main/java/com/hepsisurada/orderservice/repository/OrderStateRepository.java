package com.hepsisurada.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hepsisurada.orderservice.model.entity.OrderState;

@Repository
public interface OrderStateRepository extends JpaRepository<OrderState, Long> {

}
