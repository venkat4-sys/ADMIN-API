package com.tcs.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.UserForm;
import com.tcs.dto.AccountsResponse;
import com.tcs.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService adminService;

	@PostMapping("/CreateWorker")
	public ResponseEntity<?> createCaseWorker(@RequestBody UserForm userForm) {
		String message = adminService.createCaseworker(userForm);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}

	@GetMapping("/getAllAccounts")
	public ResponseEntity<?> getCaseWorkerAccounts() {
		List<AccountsResponse> viewAllAccounts = adminService.viewAllAccounts();
		return new ResponseEntity<>(viewAllAccounts, HttpStatus.OK);
	}

	@GetMapping("/getAccount/{userID}")
	public ResponseEntity<?> getAccount(@PathVariable Integer userID) {
		AccountsResponse data = adminService.getAccount(userID);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PutMapping("/editAccount/{userID}")
	public ResponseEntity<?> editAccount(@PathVariable Integer userID,@RequestBody UserForm userForm) {
		String Data = adminService.editContact(userID, userForm);
		return new ResponseEntity<>(Data, HttpStatus.OK);
		
	}
	
	@PutMapping("/{userId}/activate")
    public ResponseEntity<?> activateAccount(@PathVariable Integer userId) {
		adminService.activeSwitch(userId);
		return new ResponseEntity<>(HttpStatus.OK);
    }

  
}