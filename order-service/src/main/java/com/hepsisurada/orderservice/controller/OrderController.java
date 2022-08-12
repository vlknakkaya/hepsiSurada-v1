package com.hepsisurada.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hepsisurada.orderservice.model.converter.OrderConverter;
import com.hepsisurada.orderservice.model.dto.OrderDTO;
import com.hepsisurada.orderservice.model.entity.Order;
import com.hepsisurada.orderservice.model.entity.OrderState;
import com.hepsisurada.orderservice.service.OrderService;
import com.hepsisurada.orderservice.service.OrderStateService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;
	@Autowired
	private OrderConverter converter;
	@Autowired
	private OrderStateService orderStateService; 
	
	@GetMapping
	public List<OrderDTO> getAllOrders() {
		return converter.convertToDTOList(service.findAll());
	}
	
	@GetMapping("/{id}")
	public OrderDTO getOrderById(@PathVariable long id) {
		return converter.convertToDTO(service.findById(id));
	}
	
	@PostMapping
	public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(orderDTO)));
	}
	
	@PutMapping("/{id}")
	public OrderDTO updateOrderById(@PathVariable long id, @RequestBody OrderDTO orderDTO) {
		Order entity = service.findById(id);
		
		entity.setProductIdList(orderDTO.getProductIdList());
		entity.setUserId(orderDTO.getUserId());
		entity.setState(orderStateService.findById(orderDTO.getStateId()));
		
		return converter.convertToDTO(service.save(entity));
	}
	
	@DeleteMapping("/{id}")
	public void removeOrderById(@PathVariable long id) {
		service.removeById(id);
	}
	
	@GetMapping("/userId/{userId}")
	public List<OrderDTO> getOrderByUserId(@PathVariable long userId) {
		return converter.convertToDTOList(service.findByUserId(userId));
	}

	@GetMapping("/product/{productId}")
	public List<OrderDTO> getOrderByProductId(@PathVariable long productId) {
		return converter.convertToDTOList(service.findByProductIdList(productId));
	}
	
	@GetMapping("/state/{stateId}")
	public List<OrderDTO> getOrderByState(@PathVariable long stateId) {
		OrderState state = orderStateService.findById(stateId);
		return converter.convertToDTOList(service.findByState(state));
	}
	
}
