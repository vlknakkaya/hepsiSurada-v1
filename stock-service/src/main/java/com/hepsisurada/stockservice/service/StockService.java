package com.hepsisurada.stockservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.stockservice.aspect.annotation.Log;
import com.hepsisurada.stockservice.aspect.annotation.Performance;
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

	@Log
	@Performance
	public Stock findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Stock", "id", id));
	}

	@Log
	@Performance
	public Stock findByProductId(long productId) {
		return repository.findByProductId(productId).orElseThrow(() -> new EntityNotFoundException("Stock", "productId", productId));
	}

	@Log
	@Performance
	public List<Stock> findByCount(long count) {
		return repository.findByCount(count);
	}

	@Log
	@Performance
	public List<Stock> findByCountLessThanEqual(long count) {
		return repository.findByCountLessThanEqual(count);
	}

	@Log
	@Performance
	public List<Stock> findByCountGreaterThanEqual(long count) {
		return repository.findByCountGreaterThanEqual(count);
	}

	@Log
	@Performance
	public List<Stock> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public Stock save(Stock entity) {
		return repository.save(entity);
	}

	@Log
	@Performance
	public void remove(Stock stock) {
		repository.delete(stock);
	}
	
}
