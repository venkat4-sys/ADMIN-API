package com.tcs.service;

import com.tcs.dto.LoginForm;
import com.tcs.dto.UnlockForm;

public interface UserService {
	
	public String login(LoginForm loginForm);
	
	public String unlockAccount(UnlockForm unlockForm,String email);
	
	public String forgot(String email);

}
