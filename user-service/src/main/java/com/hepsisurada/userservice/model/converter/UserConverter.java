package com.hepsisurada.userservice.model.converter;

import org.springframework.stereotype.Component;

import com.hepsisurada.userservice.model.dto.UserDTO;
import com.hepsisurada.userservice.model.entity.User;

@Component
public class UserConverter implements DTOConverter<User, UserDTO> {

	@Override
	public User convertToEntity(UserDTO dto) {
		return dto == null ? null : new User(dto.getEmail(), dto.getName(), dto.getAddress());
	}

	@Override
	public UserDTO convertToDTO(User entity) {
		return entity == null ? null : new UserDTO(entity.getId(), entity.getEmail(), entity.getName(), entity.getAddress());
	}

}
