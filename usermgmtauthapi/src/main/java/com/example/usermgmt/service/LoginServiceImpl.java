package com.example.usermgmt.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usermgmt.domain.User;
import com.example.usermgmt.exception.EntityNotFoundException;
import com.example.usermgmt.model.LoginDto;
import com.example.usermgmt.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Environment env;
	
	@Override
	@Transactional
	public String login(LoginDto loginDto) {
		User user = userRepository.findByEmail(loginDto.getEmail());
		if(user==null) {
			throw new EntityNotFoundException(env.getProperty("user.id.not.found"));
		}
		
		if (!passwordEncoder.matches(new StringBuffer(loginDto.getPassword()),
				(user.getPassword()))) {
			throw new BadCredentialsException("Bad credentials");
		}
		user.setLastLoginDate(new Date());
		return "login success";
	}

}
