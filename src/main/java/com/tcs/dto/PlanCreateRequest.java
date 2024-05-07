package com.tcs.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlanCreateRequest {

	private Integer planId;

	private String planName;

	private Date planStartDate;
	
	private String planCategory;

	private Date planEndDate;

}
