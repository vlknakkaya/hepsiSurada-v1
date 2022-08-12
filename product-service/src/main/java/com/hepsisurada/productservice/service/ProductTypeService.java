package com.hepsisurada.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.productservice.exception.EntityNotFoundException;
import com.hepsisurada.productservice.model.entity.ProductType;
import com.hepsisurada.productservice.repository.ProductTypeRepository;

@Service
public class ProductTypeService {

	private ProductTypeRepository repository;

	@Autowired
	public ProductTypeService(ProductTypeRepository repository) {
		this.repository = repository;
	}

	public ProductType findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductType", "id", id));
	}
	
	public List<ProductType> findByTopTypeId(long topTypeId) {
		return repository.findByTopTypeId(topTypeId);
	}
	
	public List<ProductType> findAll() {
		return repository.findAll();
	}

	public ProductType save(ProductType entity) {
		return repository.save(entity);
	}

	public void removeById(long id) {
		repository.deleteById(id);
	}
	
}
