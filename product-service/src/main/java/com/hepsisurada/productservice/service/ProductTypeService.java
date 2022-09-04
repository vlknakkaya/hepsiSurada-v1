package com.hepsisurada.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.productservice.aspect.annotation.Log;
import com.hepsisurada.productservice.aspect.annotation.Performance;
import com.hepsisurada.productservice.exception.EntityNotFoundException;
import com.hepsisurada.productservice.model.entity.ProductType;
import com.hepsisurada.productservice.repository.ProductTypeRepository;
import com.hepsisurada.productservice.util.EmailEvent;
import com.hepsisurada.productservice.util.EventType;

@Service
public class ProductTypeService {

	private ProductTypeRepository repository;
	private KafkaProducer producer;

	@Autowired
	public ProductTypeService(ProductTypeRepository repository, KafkaProducer producer) {
		this.repository = repository;
		this.producer = producer;
	}

	@Log
	@Performance
	public ProductType findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductType", "id", id));
	}

	@Log
	@Performance
	public List<ProductType> findByTopTypeId(long topTypeId) {
		return repository.findByTopTypeId(topTypeId);
	}

	@Log
	@Performance
	public List<ProductType> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public ProductType save(ProductType entity) {
		if (entity.getId() == null) {
			producer.send(new EmailEvent(EventType.PRODUCT_TYPE_CREATED, "admin@hepsisurada", "New product type was added: " + entity.getName()));
		} else {
			producer.send(new EmailEvent(EventType.PRODUCT_TYPE_UPDATED, "admin@hepsisurada", "Product type was updated: " + entity.getId()));
		}
		return repository.save(entity);
	}

	@Log
	@Performance
	public void remove(ProductType entity) {
		producer.send(new EmailEvent(EventType.PRODUCT_TYPE_REMOVED, "admin@hepsisurada", "Product type was removed: " + entity.getId()));
		repository.delete(entity);
	}
	
}
