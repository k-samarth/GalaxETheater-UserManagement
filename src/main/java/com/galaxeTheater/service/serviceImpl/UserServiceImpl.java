package com.galaxeTheater.service.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxeTheater.dto.UserDTO;
import com.galaxeTheater.entities.Role;
import com.galaxeTheater.entities.User;
import com.galaxeTheater.exceptions.EmailAlreadyExistsException;
import com.galaxeTheater.exceptions.EmailNotFoundException;
import com.galaxeTheater.exceptions.MobileNumberAlreadyExistsException;
import com.galaxeTheater.exceptions.PasswordIncorrecrException;
import com.galaxeTheater.exceptions.UserNameAlreadyExistsException;
import com.galaxeTheater.exceptions.UsersNotFoundException;
import com.galaxeTheater.mapper.UserDTOMapper;
import com.galaxeTheater.repository.UserRepository;
import com.galaxeTheater.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository theaterRepository;

	@Autowired
	UserDTOMapper userMapper;

	Logger log = LogManager.getLogger(UserServiceImpl.class);

	// Registering a new user as admin

	@Override
	public String saveUser(User user) throws EmailAlreadyExistsException {

		if (user.getFirstName().equals(null) || user.getLastName().equals(null) || user.getMobileNumber().equals(null)
				|| user.getEmail().equals(null) || user.getPassword().equals(null) || user.getGender().equals(null)
				|| user.getUserName().equals(null) || emailAlreadyExists(user.getEmail()) == true) {
			log.error("Email already Exists");
			throw new EmailAlreadyExistsException("Email already Exists");
		} else if (mobileAlreadyExists(user.getMobileNumber())) {
			log.error("Mobile Number Already Exists");
			throw new MobileNumberAlreadyExistsException(
					"Mobile Number already exists");
		} else if (userNameAlreadyExist(user.getUserName())) {
			log.error("Mobile Number Already Exists");
			throw new UserNameAlreadyExistsException("UserName Already exists");
		} else {

			user.setRole(Role.ADMIN);
			user.setCode(generateCode());
			theaterRepository.save(user);
			log.info("Admin registration success");
			return "Admin Registration success";
		}

	}

	@Override
	public String saveuserUser(User user) {
		if (user.getFirstName().equals(null) || user.getLastName().equals(null) || user.getMobileNumber().equals(null)
				|| user.getEmail().equals(null) || user.getPassword().equals(null) || user.getGender().equals(null)
				|| user.getUserName().equals(null) || emailAlreadyExists(user.getEmail()) == true) {

			log.error("Email already Exists");
			throw new EmailAlreadyExistsException("Email already Exists" + user.getEmail());
		} else if (mobileAlreadyExists(user.getMobileNumber())) {

			log.error("Mobile Number Already Exists");
			throw new MobileNumberAlreadyExistsException(
					"Account with this mobile Number already exists" + user.getMobileNumber());
		} else if (userNameAlreadyExist(user.getUserName())) {
			log.error("Mobile Number Already Exists");
			throw new UserNameAlreadyExistsException("UserName Already exists" + user.getUserName());
		} else {
			user.setRole(Role.USER);
			user.setCode(generateCode());
			theaterRepository.save(user);
			log.info("User registration success");
			return "User registration success";
		}

	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		log.error("Getting All user Information success");
		return theaterRepository.findAll();
	}

	@Override
	public boolean getUserByEmail(String email, String password) {
		User user = theaterRepository.findByEmail(email);
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			throw new PasswordIncorrecrException("Password Incorrect,please check your password");
		}

	}

	@Override
	public String updateUser(User user) {
		User users=theaterRepository.findByEmail(user.getEmail());
		User use=theaterRepository.findByCode(users.getCode());
		use.setEmail(user.getEmail());
		use.setFirstName(user.getFirstName());
		use.setLastName(user.getLastName());
		use.setGender(user.getGender());
		use.setMobileNumber(user.getMobileNumber());
		use.setPhoneNumber(user.getPhoneNumber());
		theaterRepository.save(use);
		return "Update success";
	}

	@Override
	public String deleteUser(String email) {
		List<User> users = theaterRepository.findAll();
		String message=null;
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				theaterRepository.delete(user);
				message="Delete user success";
			} 
		}
		return message;

	}

	@Override
	public String changePassword(String email, String password) {
		User user = theaterRepository.findByEmail(email);
	
		String message=null;
		if(user==null) {
			throw new EmailNotFoundException("Email Not found");
		}
		else {
				user.setPassword(password);
				theaterRepository.save(user);
				message="Password update success";
		}
			
	
		
		return message;
	}

	@Override
	public boolean emailAlreadyExists(String email) {
		List<User> users = theaterRepository.findAll();
		boolean flag = false;
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	

	public boolean mobileAlreadyExists(String mobile) {
		List<User> users = theaterRepository.findAll();
		boolean flag = false;
		for (User user : users) {
			if (user.getMobileNumber().equals(mobile)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	
	
	public boolean userNameAlreadyExist(String userName) {
		List<User> users = theaterRepository.findAll();
		boolean flag = false;
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;

	}

	
	
	
	@Override
	public List<UserDTO> getUserDTO() {
		// TODO Auto-generated method stub
		List<User> users = theaterRepository.findAll();
		UserDTO userDto = new UserDTO();
		List<UserDTO> userDtos = new ArrayList<UserDTO>();
		try {
		for (User user : users) {
			userDto = userMapper.convertToDto(user);
			userDtos.add(userDto);
		}
		}
		catch(UsersNotFoundException e) {
			System.out.println(e);
			log.error(e);
		}
		return userDtos;
	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		HttpSession session = request.getSession();
//
//		session.setMaxInactiveInterval(600); // session timeout in seconds
//
//	}

	@Override
	public String generateCode() {
		String uniqueID = UUID.randomUUID().toString();
		return uniqueID;
	}

	@Override
	public User getUserByOnlyEmail(String email) {
		// TODO Auto-generated method stub
		return theaterRepository.findByEmail(email);
	}

	@Override
	public long generateOTP(String email) {
		Random rand = new Random();
		long upperbound = 100000;
		long random = rand.nextInt((int) upperbound);
		System.out.println("Random integer value from 0 to" + (upperbound - 1) + " : " + random);
		List<User> users = theaterRepository.findAll();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				user.setOtp(random);
				theaterRepository.save(user);
			}

		}

		return random;
	}

}
