package com.hepsisurada.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hepsisurada.userservice.aspect.annotation.Log;
import com.hepsisurada.userservice.aspect.annotation.Performance;
import com.hepsisurada.userservice.exception.EntityNotFoundException;
import com.hepsisurada.userservice.model.entity.User;
import com.hepsisurada.userservice.repository.UserRepository;
import com.hepsisurada.userservice.util.EmailEvent;

@Service
public class UserService {

	private UserRepository repository;
	private KafkaProducer producer;

	@Autowired
	public UserService(UserRepository repository, KafkaProducer producer) {
		super();
		this.repository = repository;
		this.producer = producer;
	}

	@Log
	@Performance
	public User findById(long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User", "id", id));
	}

	@Log
	@Performance
	public User findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User", "email", email));
	}

	@Log
	@Performance
	public List<User> findAll() {
		return repository.findAll();
	}

	@Log
	@Performance
	public User save(User user) {
		producer.send(new EmailEvent("admin@hepsisurada", "New user created: " + user.getEmail()));
		producer.send(new EmailEvent(user.getEmail(), "Your user has been created."));
		return repository.save(user);
	}

	@Log
	@Performance
	public void remove(User user) {
		producer.send(new EmailEvent("admin@hepsisurada", "User was removed: " + user.getEmail()));
		producer.send(new EmailEvent(user.getEmail(), "Your user has been removed."));
		repository.delete(user);
	}
	
}
