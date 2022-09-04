package com.hepsisurada.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hepsisurada.productservice.aspect.annotation.Log;
import com.hepsisurada.productservice.aspect.annotation.Performance;
import com.hepsisurada.productservice.model.converter.ProductTypeConverter;
import com.hepsisurada.productservice.model.dto.ProductTypeDTO;
import com.hepsisurada.productservice.model.entity.ProductType;
import com.hepsisurada.productservice.service.ProductTypeService;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {

	@Autowired
	private ProductTypeService service;
	@Autowired
	private ProductTypeConverter converter;

	@Log
	@Performance
	@GetMapping
	public List<ProductTypeDTO> getProductTypesByTopTypeId(@RequestParam(required = false) Long topType) {
		if (topType == null) {
			return converter.convertToDTOList(service.findAll());
		} else {
			return converter.convertToDTOList(service.findByTopTypeId(topType));
		}
	}

	@Log
	@Performance
	@GetMapping("/{id}")
	public ProductTypeDTO getProductTypeById(@PathVariable long id) {
		return converter.convertToDTO(service.findById(id));
	}

	@Log
	@Performance
	@PostMapping
	public ProductTypeDTO createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(productTypeDTO)));
	}

	@Log
	@Performance
	@PutMapping("/{id}")
	public ProductTypeDTO updateProductTypeById(@PathVariable long id, @RequestBody ProductTypeDTO productTypeDTO) {
		ProductType entity = service.findById(id);
		
		entity.setTopTypeId(productTypeDTO.getTopTypeId());
		entity.setName(productTypeDTO.getName());
		
		return converter.convertToDTO(service.save(entity));
	}

	@Log
	@Performance
	@DeleteMapping("/{id}")
	public void removeProductTypeById(long id) {
		service.removeById(id);
	}

}
