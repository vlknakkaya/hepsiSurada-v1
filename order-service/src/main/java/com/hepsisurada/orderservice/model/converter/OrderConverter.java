package com.hepsisurada.orderservice.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hepsisurada.orderservice.model.dto.OrderDTO;
import com.hepsisurada.orderservice.model.entity.Order;
import com.hepsisurada.orderservice.service.OrderStateService;

@Component
public class OrderConverter implements DTOConverter<Order, OrderDTO> {

	@Autowired
	private OrderStateService orderStateService;

	@Override
	public Order convertToEntity(OrderDTO dto) {
		return dto == null ? null
				: new Order(dto.getProductIdList(), dto.getUserId(), orderStateService.findById(dto.getStateId()));
	}

	@Override
	public OrderDTO convertToDTO(Order entity) {
		return entity == null ? null
				: new OrderDTO(entity.getId(), entity.getProductIdList(), entity.getUserId(),
						entity.getState().getId());
	}

}
