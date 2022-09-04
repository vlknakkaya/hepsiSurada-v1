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
import com.hepsisurada.userservice.util.EventType;

@Service
public class UserService {

	private UserRepository repository;
	private KafkaProducer producer;

	@Autowired
	public UserService(UserRepository repository, KafkaProducer producer) {
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
	public User save(User entity) {
		boolean isCreated = entity.getId() == null;
		
		User user = repository.save(entity);
		
		if (isCreated) {
			producer.send(new EmailEvent(EventType.USER_CREATED, "admin@hepsisurada", "New user was created: " + user));
			producer.send(new EmailEvent(EventType.USER_CREATED, user.getEmail(), "Your user has been created."));
		} else {
			producer.send(new EmailEvent(EventType.USER_UPDATED, "admin@hepsisurada", "User was updated: " + user));
			producer.send(new EmailEvent(EventType.USER_UPDATED, user.getEmail(), "Your user has been updated."));
		}
		
		return user;
	}

	@Log
	@Performance
	public void remove(User user) {
		producer.send(new EmailEvent(EventType.USER_REMOVED, "admin@hepsisurada", "User was removed: " + user));
		producer.send(new EmailEvent(EventType.USER_REMOVED, user.getEmail(), "Your user has been removed."));
		
		repository.delete(user);
	}
	
}
