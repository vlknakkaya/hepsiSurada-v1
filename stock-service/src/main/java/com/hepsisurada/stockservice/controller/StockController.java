package com.hepsisurada.stockservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hepsisurada.stockservice.model.converter.StockConverter;
import com.hepsisurada.stockservice.model.dto.StockDTO;
import com.hepsisurada.stockservice.model.entity.Stock;
import com.hepsisurada.stockservice.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService service;
	@Autowired
	private StockConverter converter;
	
	@GetMapping
	public List<StockDTO> getAllStocks() {
		return converter.convertToDTOList(service.findAll());
	}
	
	@GetMapping("/{productId}")
	public StockDTO getStockByproductId(@PathVariable long productId) {
		return converter.convertToDTO(service.findByProductId(productId));
	}
	
	@PostMapping
	public StockDTO createStock(@RequestBody StockDTO stockDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(stockDTO)));
	}
	
	@PutMapping("/{productId}")
	public StockDTO updateStockById(@PathVariable long productId, @RequestBody StockDTO stockDTO) {
		Stock entity = service.findByProductId(productId);
		
		entity.setCount(stockDTO.getCount());
		
		return converter.convertToDTO(service.save(entity));
	}

	@DeleteMapping("/{productId}")
	public void removeStockByProductId(@PathVariable long productId) {
		service.remove(service.findByProductId(productId));
	}
	
	@GetMapping("/count")
	public List<StockDTO> getStocksByCount(@RequestParam(defaultValue = "0") long value, @RequestParam(required = false) Character option) {
		if (option == null) {
			return converter.convertToDTOList(service.findByCount(value));
		} else if (option == 'l') {
			return converter.convertToDTOList(service.findByCountLessThanEqual(value));
		} else {
			return converter.convertToDTOList(service.findByCountGreaterThanEqual(value));
		}
	}
	
}
