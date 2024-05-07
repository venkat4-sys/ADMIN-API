package com.tcs.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PlanResponse {

	private Integer planId;

	private String planName;

	private String planCategory;

	private Date planStartDate;

	private Date planEndDate;

}
