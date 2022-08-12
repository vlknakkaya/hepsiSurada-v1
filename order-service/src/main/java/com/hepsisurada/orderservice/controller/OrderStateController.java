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

import com.hepsisurada.orderservice.model.converter.OrderStateConverter;
import com.hepsisurada.orderservice.model.dto.OrderStateDTO;
import com.hepsisurada.orderservice.model.entity.OrderState;
import com.hepsisurada.orderservice.service.OrderStateService;

@RestController
@RequestMapping("/orderState")
public class OrderStateController {

	@Autowired
	private OrderStateService service;
	@Autowired
	private OrderStateConverter converter;
	
	@GetMapping
	public List<OrderStateDTO> getAllOrderStates() {
		return converter.convertToDTOList(service.findAll());
	}
	
	@GetMapping("/{id}")
	public OrderStateDTO getOrderStateById(@PathVariable long id) {
		return converter.convertToDTO(service.findById(id));
	}
	
	@PostMapping
	public OrderStateDTO createOrderState(@RequestBody OrderStateDTO orderStateDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(orderStateDTO)));
	}
	
	@PutMapping("/{id}")
	public OrderStateDTO updateOrderStateById(@PathVariable long id, @RequestBody OrderStateDTO orderStateDTO) {
		OrderState entity = service.findById(id);
		
		entity.setName(orderStateDTO.getName());
		
		return converter.convertToDTO(service.save(entity));
	}
	
	@DeleteMapping("/{id}")
	public void removeOrderStateById(@PathVariable long id) {
		service.removeById(id);
	}
	
}
