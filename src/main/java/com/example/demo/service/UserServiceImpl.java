package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.CreateUserModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String createUser(CreateUserModel createUserModel) {
		User userEntity = UserMapper.INSTANCE.createUserModelToUserEntity(createUserModel);
		userEntity = userRepository.save(userEntity);
		return userEntity.getId();
	}

	@Override
	public UserModel readUserById(String userId) {
		Optional<User> findById = userRepository.findById(userId);
		if (findById.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with id [%s] not found.", userId));
		}
		
		return UserMapper.INSTANCE.userEntityToUserModel(findById.get());
	}

}
