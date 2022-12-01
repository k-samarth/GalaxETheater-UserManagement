package com.galaxeTheater.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galaxeTheater.dto.UserDTO;
import com.galaxeTheater.entities.ImageModel;
import com.galaxeTheater.entities.Role;
import com.galaxeTheater.entities.User;
import com.galaxeTheater.exceptions.EmailAlreadyExistsException;
import com.galaxeTheater.exceptions.EmailNotFoundException;
import com.galaxeTheater.exceptions.MobileNumberAlreadyExistsException;
import com.galaxeTheater.exceptions.PasswordIncorrecrException;
import com.galaxeTheater.exceptions.UserDoesNotExistsException;
import com.galaxeTheater.exceptions.UserNameAlreadyExistsException;
import com.galaxeTheater.exceptions.UsersNotFoundException;
import com.galaxeTheater.repository.ImageRepository;
import com.galaxeTheater.service.EmailService;
import com.galaxeTheater.service.ImageService;
import com.galaxeTheater.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService theaterService;

	@Autowired
	EmailService emailService;

	@Autowired
	ImageService imageService;
	
	@Autowired
	ImageRepository imageRepository;
	

	//Register/Save User as Admin
	
	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		
	
		
			try {
				return new ResponseEntity<String>(theaterService.saveUser(user),HttpStatus.ACCEPTED);
			}
			catch(EmailAlreadyExistsException e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			}
			catch(UserNameAlreadyExistsException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
			catch(MobileNumberAlreadyExistsException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
		
	}
	
	//Register/Save User as User
	
	@PostMapping("/user/user")
	public ResponseEntity<String> saveUser1(@RequestBody User user) {
		
			try {
				return new ResponseEntity<String>(theaterService.saveuserUser(user),HttpStatus.ACCEPTED);
			}
			catch(EmailAlreadyExistsException e) {
				
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
			}
			catch(UserNameAlreadyExistsException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
			catch(MobileNumberAlreadyExistsException e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
	}

	//Get all Users 
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getUsers() {
		try {
			return new ResponseEntity<List<User>>(theaterService.getUser(),HttpStatus.OK);
		}
		catch(UsersNotFoundException e) {
			return new ResponseEntity<List<User>>(HttpStatus.CONFLICT);
		}
	
	}
	
	//Get User Information by email
	
	@GetMapping("/user/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
		try {
			return new ResponseEntity<User>(theaterService.getUserByOnlyEmail(email),HttpStatus.OK);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

	//Get user information by mail and Password
	
	@GetMapping("/user/{email}/{password}")
	public ResponseEntity<Boolean> getUserByMail(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {
			return new ResponseEntity<Boolean>(theaterService.getUserByEmail(email,password),HttpStatus.OK);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		catch(PasswordIncorrecrException e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
	}

	//Get all userDTOs information
	
	@GetMapping("/user/dto")
	public ResponseEntity<List<UserDTO>> getUserDTO() {
		try {
			return new ResponseEntity<List<UserDTO>>(theaterService.getUserDTO(),HttpStatus.OK);
		}
		catch(UsersNotFoundException e) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.CONFLICT);
		}
		
	}

	//Edit User Information
	
	@PutMapping("/user")
	public ResponseEntity<String> editUser(@RequestBody User user) {
		try {
			return new ResponseEntity<String>(theaterService.updateUser(user),HttpStatus.ACCEPTED);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(UserNameAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(MobileNumberAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	
	//Delete User Information 
	
	@DeleteMapping("/user/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
		try {
			return new ResponseEntity<String>(theaterService.deleteUser(email),HttpStatus.ACCEPTED);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	//Update password of user by validating mail and password

	@PostMapping("/user/{email}/{password}")
	public ResponseEntity<String> updatePassword(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {
			return new ResponseEntity<String>(theaterService.changePassword(email, password),HttpStatus.ACCEPTED);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		catch(PasswordIncorrecrException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	//Get User Info for View Profile section validated by email and password
	
	@GetMapping("/user/settings/{email}/{password}")
	public ResponseEntity<User> getUserByEmailOnly(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {
			return new ResponseEntity<User>(theaterService.getUserByOnlyEmail(email),HttpStatus.OK);
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		catch(PasswordIncorrecrException e) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		
	}

	//Send OTP to email by validating email existence
	
	@GetMapping("/sendMail/{email}")
	public ResponseEntity<String> sendMail(@PathVariable("email") String email) {
		try {
			return new ResponseEntity<String>(emailService.sendSimpleMail(email),HttpStatus.OK) ;
		}
		catch(EmailNotFoundException e) {
			return new ResponseEntity<String>("Invalid Email",HttpStatus.BAD_REQUEST);
		}
		catch(UserDoesNotExistsException e) {
			return new ResponseEntity<String>("User Not exists with the Mail",HttpStatus.BAD_REQUEST);
		}
	}
	
	//send welcome email by validating email
	
	@GetMapping("/sendWelcomeMail/{email}")
	public String sendWelcomeMail(@PathVariable("email") String email) {
		String status=emailService.sendWelcomeMail(email);
		return status;
	}

	
}
