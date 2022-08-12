package com.hepsisurada.productservice.model.converter;

import org.springframework.stereotype.Component;

import com.hepsisurada.productservice.model.dto.ProductTypeDTO;
import com.hepsisurada.productservice.model.entity.ProductType;

@Component
public class ProductTypeConverter implements DTOConverter<ProductType, ProductTypeDTO> {

	@Override
	public ProductType convertToEntity(ProductTypeDTO dto) {
		return dto == null ? null : new ProductType(dto.getTopTypeId(), dto.getName());
	}

	@Override
	public ProductTypeDTO convertToDTO(ProductType entity) {
		return entity == null ? null : new ProductTypeDTO(entity.getId(), entity.getTopTypeId(), entity.getName());
	}

}
