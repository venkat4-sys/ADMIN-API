package com.tcs.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.config.AppProperties;
import com.tcs.constants.AppConstants;
import com.tcs.dto.LoginForm;
import com.tcs.dto.UnlockForm;
import com.tcs.entity.UserEntity;
import com.tcs.repo.UserRepository;
import com.tcs.service.UserService;
import com.tcs.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Autowired
	private AccountServiceimpl adminServiceimpl;
	
	@Autowired
	private AppProperties appProperties;

	@Override
	public String login(LoginForm loginForm) {
		UserEntity user = userRepository.findByEmailIdAndPassword(loginForm.getEmailId(), loginForm.getPassword());
		if(user==null) {
			return AppConstants.INVALID_CREDENTIALS;
		}
		if(user.isAccSwitch() &&  AppConstants.UNLOCKED_STATUS.equals(user.getStatus())) {
		   return AppConstants.SUCCESS;
		}else {
		return AppConstants.LOCKED_ACCOUNT	;
		}
	}
	
	@Override
	public String unlockAccount(UnlockForm unlockForm,String email) {
		
		UserEntity entity = userRepository.findByEmailId(email);
		if(entity!=null) {
			if(entity.getPassword().equals(unlockForm.getNewPassword())) {
				entity.setPassword(unlockForm.getNewPassword());
				entity.setStatus(AppConstants.ACCOUNT_UNLOCK_STATUS);
				userRepository.save(entity);
			}
		}
		Map<String, String> messages = appProperties.getMessages();
		
		
		String passWordUpdatedMsg = messages.get(AppConstants.PASSWORD_UPDATED_MSG);
		return passWordUpdatedMsg;
	}
	
	@Override
	public String forgot(String email) {
		Map<String, String> messages = appProperties.getMessages();
		UserEntity entity = userRepository.findByEmailId(email);
		if(entity==null) {
			String emailNotFound = messages.get(AppConstants.EMAIL_NOT_FOUND);
			return emailNotFound;
		}else {
			String to=email;
			
			String subjectMsg = messages.get(AppConstants.DETAILS_CHECK);
			
			String subject=subjectMsg;
			
			String forGotFileName = messages.get(AppConstants.FORGOT_FILE_NAME);
			String fileName=forGotFileName;
			String body = adminServiceimpl.readEmailBody(fileName, entity);
			emailUtils.sendEmail(to, subject, body);
			
			String emailDetails = messages.get(AppConstants.EMAIL_CHECK);
			return emailDetails;
		}
	}
}
