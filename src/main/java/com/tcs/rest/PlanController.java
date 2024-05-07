package com.tcs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.PlanCreateRequest;
import com.tcs.dto.PlanResponse;
import com.tcs.service.PlanService;

@RestController
public class PlanController {

	@Autowired
	private PlanService planService;

	@PostMapping("/CreatePlan")
	public ResponseEntity<?> createPlan(@RequestBody PlanCreateRequest planCreateRequest) {
		String message = planService.createPlan(planCreateRequest);

		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/GetAllPlans")
	public ResponseEntity<?> getAllThePlans() {
		List<PlanResponse> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/GetAllPlans")
	public ResponseEntity<?> getPlan(Integer planId) {
		PlanResponse plan = planService.getPlan(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@GetMapping("/GetAllPlans")
	public ResponseEntity<?> editPlan(Integer planId, PlanCreateRequest plaCreateRequest) {
		String message = planService.editPlan(planId, plaCreateRequest);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
