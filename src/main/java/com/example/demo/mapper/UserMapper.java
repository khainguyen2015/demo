package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.User;
import com.example.demo.model.CreateUserModel;
import com.example.demo.model.UserModel;

@Mapper
public interface UserMapper {
	
	public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	User createUserModelToUserEntity(CreateUserModel createUserModel);
	
	UserModel userEntityToUserModel(User userEntity);

}
