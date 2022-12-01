package com.galaxeTheater.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="userDetails")
@NoArgsConstructor
public class User {
	public User(String userName, String firstName, String lastName, String email, String mobileNumber,
			String phoneNumber, String gender, String password, String confirmPassword, Role role) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String userName;
	
	private String code;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String mobileNumber;
	
	@Column(nullable = true)
	private String phoneNumber;
	
	private String gender;
	private String password;
	private String confirmPassword;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private long otp;

}
