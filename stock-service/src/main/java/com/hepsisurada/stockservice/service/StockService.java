package com.hepsisurada.stockservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.stockservice.exception.EntityNotFoundException;
import com.hepsisurada.stockservice.model.entity.Stock;
import com.hepsisurada.stockservice.repository.StockRepository;

@Service
public class StockService {

	private StockRepository repository;

	@Autowired
	public StockService(StockRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Stock findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stock", "id", id));
	}
	
	public Stock findByProductId(long productId) {
		return repository.findByProductId(productId).orElseThrow(() -> new EntityNotFoundException("Stock", "productId", productId));
	}
	
	public List<Stock> findByCount(long count) {
		return repository.findByCount(count);
	}
	
	public List<Stock> findByCountLessThanEqual(long count) {
		return repository.findByCountLessThanEqual(count);
	}
	
	public List<Stock> findByCountGreaterThanEqual(long count) {
		return repository.findByCountGreaterThanEqual(count);
	}
	
	public List<Stock> findAll() {
		return repository.findAll();
	}
	
	public Stock save(Stock entity) {
		return repository.save(entity);
	}
	
	public void remove(Stock stock) {
		repository.delete(stock);
	}
	
}
