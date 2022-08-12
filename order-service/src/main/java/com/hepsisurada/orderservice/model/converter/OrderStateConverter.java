package com.hepsisurada.orderservice.model.converter;

import org.springframework.stereotype.Component;

import com.hepsisurada.orderservice.model.dto.OrderStateDTO;
import com.hepsisurada.orderservice.model.entity.OrderState;

@Component
public class OrderStateConverter implements DTOConverter<OrderState, OrderStateDTO> {

	@Override
	public OrderState convertToEntity(OrderStateDTO dto) {
		return dto == null ? null : new OrderState(dto.getName());
	}

	@Override
	public OrderStateDTO convertToDTO(OrderState entity) {
		return entity == null ? null : new OrderStateDTO(entity.getId(), entity.getName());
	}

}
