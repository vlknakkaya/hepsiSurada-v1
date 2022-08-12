package com.hepsisurada.stockservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hepsisurada.stockservice.model.entity.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	Optional<Stock> findByProductId(long productId);
	
	List<Stock> findByCount(long count);
	
	List<Stock> findByCountLessThanEqual(long count);
	
	List<Stock> findByCountGreaterThanEqual(long count);
	
}
