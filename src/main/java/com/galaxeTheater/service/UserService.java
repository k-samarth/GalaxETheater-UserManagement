package com.galaxeTheater.service;

import java.util.List;

import com.galaxeTheater.dto.UserDTO;
import com.galaxeTheater.entities.User;
import com.galaxeTheater.exceptions.EmailAlreadyExistsException;

public interface UserService {
	public String saveUser(User user) throws EmailAlreadyExistsException;
	public List<User> getUser();
	public boolean getUserByEmail(String email,String password);
	public List<UserDTO> getUserDTO();
	public String updateUser(User user);
	public String deleteUser(String email);
	public String changePassword(String email,String password);
	public boolean emailAlreadyExists(String email);
	public String generateCode();
	public User getUserByOnlyEmail(String email);
	public String saveuserUser(User user);
	public long generateOTP(String email);
}
