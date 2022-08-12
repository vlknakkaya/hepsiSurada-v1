package com.hepsisurada.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hepsisurada.productservice.exception.EntityNotFoundException;
import com.hepsisurada.productservice.model.entity.Product;
import com.hepsisurada.productservice.model.entity.ProductType;
import com.hepsisurada.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository repository;
	
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product", "id", id));
	}
	
	public List<Product> findByType(ProductType type) {
		return repository.findByType(type);
	}
	
	public List<Product> findByPrice(double price) {
		return repository.findByPrice(price);
	}
	
	public List<Product> findByPriceLessThanEqual(double price) {
		return repository.findByPriceLessThanEqual(price);
	}
	
	public List<Product> findByPriceGreaterThanEqual(double price) {
		return repository.findByPriceGreaterThanEqual(price);
	}
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product save(Product entity) {
		return repository.save(entity);
	}
	
	public void removeById(long id) {
		repository.deleteById(id);
	}

}
