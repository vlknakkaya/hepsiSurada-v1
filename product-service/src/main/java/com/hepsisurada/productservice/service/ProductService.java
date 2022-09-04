package com.hepsisurada.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.productservice.aspect.annotation.Log;
import com.hepsisurada.productservice.aspect.annotation.Performance;
import com.hepsisurada.productservice.exception.EntityNotFoundException;
import com.hepsisurada.productservice.model.entity.Product;
import com.hepsisurada.productservice.model.entity.ProductType;
import com.hepsisurada.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;
	
	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	@Log
	@Performance
	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product", "id", id));
	}

	@Log
	@Performance
	public List<Product> findByType(ProductType type) {
		return repository.findByType(type);
	}

	@Log
	@Performance
	public List<Product> findByPrice(double price) {
		return repository.findByPrice(price);
	}

	@Log
	@Performance
	public List<Product> findByPriceLessThanEqual(double price) {
		return repository.findByPriceLessThanEqual(price);
	}

	@Log
	@Performance
	public List<Product> findByPriceGreaterThanEqual(double price) {
		return repository.findByPriceGreaterThanEqual(price);
	}

	@Log
	@Performance
	public List<Product> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public Product save(Product entity) {
		return repository.save(entity);
	}

	@Log
	@Performance
	public void removeById(long id) {
		repository.deleteById(id);
	}

}
