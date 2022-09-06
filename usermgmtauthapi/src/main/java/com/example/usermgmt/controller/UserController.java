package com.example.usermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usermgmt.model.UserDto;
import com.example.usermgmt.model.UserResponse;
import com.example.usermgmt.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<String>(userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> removeUser(@PathVariable Integer id){
		return new ResponseEntity<Boolean>(userService.removeUser(id),HttpStatus.ACCEPTED);
	}
	
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
		return new ResponseEntity<String>(userService.updateUser(userDto),HttpStatus.OK);
	}
	
	@GetMapping
	public List<UserResponse> users(){
		return userService.getUsers();
	}
	
}
