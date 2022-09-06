package com.example.usermgmt.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.usermgmt.domain.User;
import com.example.usermgmt.exception.DuplicateEmailIdException;
import com.example.usermgmt.exception.EntityNotFoundException;
import com.example.usermgmt.model.UserDto;
import com.example.usermgmt.model.UserResponse;
import com.example.usermgmt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Environment env;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public String createUser(UserDto userDto) {
		checkDuplicateEmail(userDto.getEmail());
		try {
			User user = new User();
			user.setEmail(userDto.getEmail());
			user.setName(userDto.getName());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);
			return env.getProperty("user.create.success");
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Boolean removeUser(Integer id) {
		checkUserExist(id);
		userRepository.delete(id);
		return true;
	}

	@Override
	public String updateUser(UserDto userDto) {
		checkUserExist(userDto.getId());
		checkDuplicateEmail(userDto.getEmail());
		try {
			User user = userRepository.findById(userDto.getId());
			user.setName(userDto.getName());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			if (!user.getEmail().equals(userDto.getEmail())) {
				user.setEmail(userDto.getEmail());
			}
			userRepository.save(user);
		} catch (DataAccessException e) {
			throw new RuntimeException(e);
		}
		return env.getProperty("user.update.success");
	}

	private void checkUserExist(Integer id) {
		boolean exists = userRepository.exists(id);
		if (!exists) {
			throw new EntityNotFoundException(env.getProperty("user.id.not.found"));
		}
	}

	private void checkDuplicateEmail(String email) {
		if (email != null) {
			User accountByEmail = userRepository.findByEmail(email);
			if (accountByEmail != null) {
				throw new DuplicateEmailIdException(env.getProperty("email.duplicate.error"));
			}
		}
	}

	@Override
	public List<UserResponse> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> {
			UserResponse response = new UserResponse();
			response.setEmail(user.getEmail());
			response.setName(user.getName());
			response.setLastLoggedInDate(user.getLastLoginDate());
			return response;
		}).collect(Collectors.toList());
	}
}
