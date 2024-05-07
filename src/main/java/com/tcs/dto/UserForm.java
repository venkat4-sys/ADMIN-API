package com.tcs.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserForm {
	
	private Integer userId;

	private String fullName;

	private String emailId;

	private Long mobileNumber;

	private String gender;

	private Date dateOfBirth;

	private String ssn;

}
