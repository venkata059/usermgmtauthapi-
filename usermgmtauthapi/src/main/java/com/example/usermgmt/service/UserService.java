package com.example.usermgmt.service;

import java.util.List;

import com.example.usermgmt.model.UserDto;
import com.example.usermgmt.model.UserResponse;

public interface UserService {

	String createUser(UserDto userDto);

	Boolean removeUser(Integer id);

	String updateUser(UserDto userDto);

	List<UserResponse> getUsers();

}
