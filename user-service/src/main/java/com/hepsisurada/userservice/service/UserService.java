package com.hepsisurada.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.userservice.exception.EntityNotFoundException;
import com.hepsisurada.userservice.model.entity.User;
import com.hepsisurada.userservice.repository.UserRepository;

@Service
public class UserService {

	private UserRepository repository;

	@Autowired
	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	public User findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", "id", id));
	}
	
	public User findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User", "email", email));
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User save(User user) {
		return repository.save(user);
	}
	
	public void removeById(long id) {
		repository.deleteById(id);
	}
	
}
