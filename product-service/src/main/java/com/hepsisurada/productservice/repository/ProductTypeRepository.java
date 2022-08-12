package com.hepsisurada.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hepsisurada.productservice.model.entity.ProductType;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
	
	List<ProductType> findByTopTypeId(long topTypeId);

}
