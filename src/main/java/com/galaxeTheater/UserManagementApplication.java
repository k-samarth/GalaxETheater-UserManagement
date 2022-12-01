package com.galaxeTheater;


import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.galaxeTheater.mapper.UserDTOMapper;
import com.galaxeTheater.service.serviceImpl.UserServiceImpl;

@SpringBootApplication
public class UserManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	
//	@Bean
//	public UserDTOMapper userDtoMapper() {
//		return new UserDTOMapperImpl();
//	}

}
