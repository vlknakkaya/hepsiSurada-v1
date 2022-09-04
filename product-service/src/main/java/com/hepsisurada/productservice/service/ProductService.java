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
import com.hepsisurada.productservice.util.EmailEvent;
import com.hepsisurada.productservice.util.EventType;

@Service
public class ProductService {

	private ProductRepository repository;
	private KafkaProducer producer;
	
	@Autowired
	public ProductService(ProductRepository repository, KafkaProducer producer) {
		this.repository = repository;
		this.producer = producer;
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
		boolean isCreated = entity.getId() == null;
		
		Product product = repository.save(entity);
		
		if (isCreated) {
			producer.send(new EmailEvent(EventType.PRODUCT_CREATED, "admin@hepsisurada", "New product was added: " + product));
		} else {
			producer.send(new EmailEvent(EventType.PRODUCT_UPDATED, "admin@hepsisurada", "Product was updated: " + product));
		}
		
		return product;
	}

	@Log
	@Performance
	public void remove(Product entity) {
		producer.send(new EmailEvent(EventType.PRODUCT_REMOVED, "admin@hepsisurada", "Product was removed: " + entity));
		
		repository.delete(entity);
	}

}
