package com.tcs.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.dto.LoginForm;
import com.tcs.dto.UnlockForm;
import com.tcs.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService managementService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginForm loginForm) {

		String status = managementService.login(loginForm);
		if (status.equals("SUCCESS")) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/forgot/{email}")
	public ResponseEntity<?> forgot(@PathVariable String email) {
		String status = managementService.forgot(email);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PostMapping("/unlockAcc/{EMAIL}")
	public ResponseEntity<?> unlockAccount(@RequestBody UnlockForm unlockForm, @PathVariable String EMAIL) {
		String unlock = managementService.unlockAccount(unlockForm, EMAIL);
		return new ResponseEntity<>(unlock, HttpStatus.OK);
	}

}
