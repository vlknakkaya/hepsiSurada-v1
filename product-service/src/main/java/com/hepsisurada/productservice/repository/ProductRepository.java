package com.hepsisurada.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hepsisurada.productservice.model.entity.Product;
import com.hepsisurada.productservice.model.entity.ProductType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByType(ProductType type);
	
	List<Product> findByPrice(double price);
	
	List<Product> findByPriceLessThanEqual(double price);
	
	List<Product> findByPriceGreaterThanEqual(double price);
	
}
