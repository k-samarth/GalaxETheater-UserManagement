package com.galaxeTheater.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String code;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String phoneNumber;
	private String gender;

}
