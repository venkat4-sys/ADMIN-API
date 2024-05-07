package com.tcs.service;

import java.util.List;

import com.tcs.dto.PlanCreateRequest;
import com.tcs.dto.PlanResponse;

public interface PlanService {
	
	public String createPlan(PlanCreateRequest planCreateRequest);
	
	public List<PlanResponse> getAllPlans();
	
	public PlanResponse getPlan(Integer planId);
	
	public String editPlan(Integer planId,PlanCreateRequest PlanCreateRequest);
	
	public void AccSwitch(Integer planId);

}
