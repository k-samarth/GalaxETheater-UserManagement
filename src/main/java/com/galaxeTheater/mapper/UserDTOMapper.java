package com.galaxeTheater.mapper;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import org.springframework.stereotype.Component;

import com.galaxeTheater.dto.UserDTO;
import com.galaxeTheater.entities.User;




public interface UserDTOMapper {

	
	@Mapping(target="code",source="user.code")
	@Mapping(target="firstName",source="user.firstName")
	@Mapping(target="lastName",source="user.lastName")
	@Mapping(target="email",source="user.email")
	@Mapping(target="mobileNumber",source="user.MobileNumber")
	@Mapping(target="phoneNumber",source="user.phoneNumber")
	@Mapping(target="gender",source="user.gender")
	public UserDTO convertToDto(User user);
	
	
	@Mapping(target="code",source="code")
	@Mapping(target="firstName",source="firstName")
	@Mapping(target="lastName",source="lastName")
	@Mapping(target="email",source="email")
	@Mapping(target="mobileNumber",source="MobileNumber")
	@Mapping(target="phoneNumber",source="phoneNumber")
	@Mapping(target="gender",source="gender")
	public User convertUser(UserDTO userDto);
	

}
