package com.example.demo.service;

import com.example.demo.model.CreateUserModel;
import com.example.demo.model.UserModel;

public interface UserService {
	
	public String createUser(CreateUserModel createUserModel);
	
	public UserModel readUserById(String userId);
	
}
