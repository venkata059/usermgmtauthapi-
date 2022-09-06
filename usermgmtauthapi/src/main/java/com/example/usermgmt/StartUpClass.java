package com.example.usermgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Startup class of spring boot application
 * 
 * @EnableTransactionManagement : to Enable transaction in an application
 * @PropertySource : used to load messages.properties in spring container
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableTransactionManagement
@PropertySource(value = { "classpath:messages.properties" })
public class StartUpClass {

	public static void main(String[] args) {
		SpringApplication.run(StartUpClass.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
