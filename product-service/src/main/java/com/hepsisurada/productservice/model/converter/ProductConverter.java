package com.hepsisurada.productservice.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hepsisurada.productservice.model.dto.ProductDTO;
import com.hepsisurada.productservice.model.entity.Product;
import com.hepsisurada.productservice.service.ProductTypeService;

@Component
public class ProductConverter implements DTOConverter<Product, ProductDTO> {
	
	@Autowired
	private ProductTypeService productTypeService;

	@Override
	public Product convertToEntity(ProductDTO dto) {
		return dto == null ? null : new Product(productTypeService.findById(dto.getTypeId()), dto.getName(), dto.getPrice());
	}

	@Override
	public ProductDTO convertToDTO(Product entity) {
		return entity == null ? null : new ProductDTO(entity.getId(), entity.getType().getId(), entity.getName(), entity.getPrice());
	}

}
