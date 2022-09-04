package com.hepsisurada.orderservice.model.converter;

import org.springframework.stereotype.Component;

import com.hepsisurada.orderservice.aspect.annotation.Log;
import com.hepsisurada.orderservice.aspect.annotation.Performance;
import com.hepsisurada.orderservice.model.dto.OrderStateDTO;
import com.hepsisurada.orderservice.model.entity.OrderState;

@Component
public class OrderStateConverter implements DTOConverter<OrderState, OrderStateDTO> {

	@Log
	@Performance
	@Override
	public OrderState convertToEntity(OrderStateDTO dto) {
		return dto == null ? null : new OrderState(dto.getName());
	}

	@Log
	@Performance
	@Override
	public OrderStateDTO convertToDTO(OrderState entity) {
		return entity == null ? null : new OrderStateDTO(entity.getId(), entity.getName());
	}

}
