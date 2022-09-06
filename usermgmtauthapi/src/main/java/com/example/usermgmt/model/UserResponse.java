package com.example.usermgmt.model;

import java.util.Date;

import lombok.Data;

@Data
public class UserResponse {

	private String name;

	private String email;
	
	private Date lastLoggedInDate;
}

