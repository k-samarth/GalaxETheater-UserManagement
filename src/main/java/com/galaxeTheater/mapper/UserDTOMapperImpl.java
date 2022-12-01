package com.galaxeTheater.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.galaxeTheater.dto.UserDTO;
import com.galaxeTheater.entities.User;

@Component
public class UserDTOMapperImpl implements UserDTOMapper{

	@Override
	public UserDTO convertToDto(User user) {
	if(user==null) {
		return null;
	}
	UserDTO userDto=new UserDTO();
	userDto.setCode(user.getCode());
	userDto.setEmail(user.getEmail());
	userDto.setFirstName(user.getFirstName());
	userDto.setGender(user.getGender());
	userDto.setLastName(user.getLastName());
	userDto.setMobileNumber(user.getMobileNumber());
	userDto.setPhoneNumber(user.getPhoneNumber());
	
	return userDto;
		
	}

	@Override
	public User convertUser(UserDTO userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
