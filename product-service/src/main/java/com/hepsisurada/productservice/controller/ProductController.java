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
import com.hepsisurada.productservice.model.converter.ProductConverter;
import com.hepsisurada.productservice.model.dto.ProductDTO;
import com.hepsisurada.productservice.model.entity.Product;
import com.hepsisurada.productservice.service.ProductService;
import com.hepsisurada.productservice.service.ProductTypeService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	private ProductConverter converter;
	@Autowired
	private ProductTypeService productTypeService;

	@Log
	@Performance
	@GetMapping
	public List<ProductDTO> getAllProducts() {
		return converter.convertToDTOList(service.findAll());
	}

	@Log
	@Performance
	@GetMapping("/{id}")
	public ProductDTO getProductById(@PathVariable long id) {
		return converter.convertToDTO(service.findById(id));
	}

	@Log
	@Performance
	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(productDTO)));
	}

	@Log
	@Performance
	@PutMapping("/{id}")
	public ProductDTO updateProductById(@PathVariable long id, @RequestBody ProductDTO productDTO) {
		Product entity = service.findById(id);
		
		entity.setName(productDTO.getName());
		entity.setPrice(productDTO.getPrice());
		entity.setType(productTypeService.findById(productDTO.getTypeId()));
		
		return converter.convertToDTO(service.save(entity));
	}

	@Log
	@Performance
	@DeleteMapping("/{id}")
	public void removeProductById(@PathVariable long id) {
		Product entity = service.findById(id);
		service.remove(entity);
	}

	@Log
	@Performance
	@GetMapping("/type")
	public List<ProductDTO> getProductsByTypeId(@RequestParam(defaultValue = "0") long id) {
		return converter.convertToDTOList(service.findByType(productTypeService.findById(id)));
	}

	@Log
	@Performance
	@GetMapping("/price")
	public List<ProductDTO> getProductsByPrice(@RequestParam(defaultValue = "0") double value, @RequestParam(required = false) Character option) {
		if (option == null) {
			return converter.convertToDTOList(service.findByPrice(value));
		} else if (option == 'l') {
			return converter.convertToDTOList(service.findByPriceLessThanEqual(value));
		} else {
			return converter.convertToDTOList(service.findByPriceGreaterThanEqual(value));
		}
	}
	
}
