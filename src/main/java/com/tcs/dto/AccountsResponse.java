package com.tcs.dto;

import lombok.Data;

@Data
public class AccountsResponse {
	
	private Integer userId;

	private String fullName;

	private String emailId;

	private Long mobileNumber;
	
	private String gender;
	
	private String ssn;

}
