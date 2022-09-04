package com.hepsisurada.stockservice.model.converter;

import org.springframework.stereotype.Component;

import com.hepsisurada.stockservice.aspect.annotation.Log;
import com.hepsisurada.stockservice.aspect.annotation.Performance;
import com.hepsisurada.stockservice.model.dto.StockDTO;
import com.hepsisurada.stockservice.model.entity.Stock;

@Component
public class StockConverter implements DTOConverter<Stock, StockDTO> {

	@Log
	@Performance
	@Override
	public Stock convertToEntity(StockDTO dto) {
		return dto == null ? null : new Stock(dto.getProductId(), dto.getCount());
	}

	@Log
	@Performance
	@Override
	public StockDTO convertToDTO(Stock entity) {
		return entity == null ? null : new StockDTO(entity.getId(), entity.getProductId(), entity.getCount());
	}

}
