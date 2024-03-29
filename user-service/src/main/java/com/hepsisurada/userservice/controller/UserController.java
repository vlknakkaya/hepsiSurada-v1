package com.hepsisurada.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hepsisurada.userservice.aspect.annotation.Log;
import com.hepsisurada.userservice.aspect.annotation.Performance;
import com.hepsisurada.userservice.model.converter.UserConverter;
import com.hepsisurada.userservice.model.dto.UserDTO;
import com.hepsisurada.userservice.model.entity.User;
import com.hepsisurada.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private UserConverter converter;
	
	@Log
	@Performance
	@GetMapping
	public List<UserDTO> getAllUsers() {
		return converter.convertToDTOList(service.findAll());
	}

	@Log
	@Performance
	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable long id) {
		return converter.convertToDTO(service.findById(id));
	}

	@Log
	@Performance
	@PostMapping
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		return converter.convertToDTO(service.save(converter.convertToEntity(userDTO)));
	}

	@Log
	@Performance
	@PutMapping("/{id}")
	public UserDTO updateUserById(@PathVariable long id, @RequestBody UserDTO userDTO) {
		User entity = service.findById(id);
		
		entity.setEmail(userDTO.getEmail());
		entity.setName(userDTO.getName());
		entity.setAddress(userDTO.getAddress());
		
		return converter.convertToDTO(service.save(entity));
	}

	@Log
	@Performance
	@DeleteMapping("/{id}")
	public void removeUserById(@PathVariable long id) {
		User entity = service.findById(id);
		service.remove(entity);
	}

	@Log
	@Performance
	@GetMapping("/email/{email}")
	public UserDTO getUserByEmail(@PathVariable String email) {
		return converter.convertToDTO(service.findByEmail(email));
	}
	
}
