package com.tcs.service;

import java.util.List;

import com.tcs.dto.UserForm;
import com.tcs.dto.AccountsResponse;

public interface AccountService {
	
	public String createCaseworker(UserForm userForm);
	
	public List<AccountsResponse> viewAllAccounts();
	
	public AccountsResponse getAccount(Integer AccountId);
	
	public String editContact(Integer AccountId,UserForm userForm);
	
	public void activeSwitch(Integer AccountId);
	

}
