package com.tcs.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.dto.UserForm;
import com.tcs.config.AppProperties;
import com.tcs.constants.AppConstants;
import com.tcs.dto.AccountsResponse;
import com.tcs.entity.RoleEntity;
import com.tcs.entity.UserEntity;
import com.tcs.repo.RoleRepository;
import com.tcs.repo.UserRepository;
import com.tcs.service.AccountService;
import com.tcs.utils.EmailUtils;

@Service
public class AccountServiceimpl implements AccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private   AppProperties appProperties;         

	@Override
	public String createCaseworker(UserForm userForm) {

		Map<String, String> messages = appProperties.getMessages();
		
		UserEntity entity = new UserEntity();

		modelMapper.map(userForm, entity);
		entity.setAccSwitch(true);
		entity.setStatus(AppConstants.LOCKED_STATUS);
		entity.setCreatedBy(null);

		// random password
		String randomPassword = generateRandomPassword(6);

		entity.setPassword(randomPassword);

		RoleEntity role = roleRepository.findById(2).get();

		entity.setRole(role);
		int loggedInAdminId = getLoggedInAdminId(); // Implement this method to get admit ID

		// Set createdBy to the ID of the admin user
		entity.setCreatedBy(loggedInAdminId);

		UserEntity record = userRepository.save(entity);

		if (record.getUserId() != null) {

			String to = record.getEmailId();
			
			
			String RegistrationMsg = messages.get(AppConstants.REG_SUCCESS);
			String subject = RegistrationMsg;
            
			String file = messages.get(AppConstants.UNLOCK_ACCOUNT_FILE);
			String fileName = file;

			String emailBody = readEmailBody(fileName, record);

			emailUtils.sendEmail(to, subject, emailBody);
		}
		String accountMsg = messages.get(AppConstants.ACCOUNT_CREAT_SUCCESS);
		return accountMsg;
	}

	private int getLoggedInAdminId() {

		return 1;
	}

	private static String generateRandomPassword(int length) {

		String characters = AppConstants.RANDOM_TEXT;
		StringBuilder password = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < length; i++) {

			int index = random.nextInt(characters.length());

			password.append(characters.charAt(index));
		}
		return password.toString();
	}

	public  String readEmailBody(String fileName, UserEntity user) {

		String mailbody = null;

		StringBuffer buffer = new StringBuffer();
		Path path = Paths.get(fileName);
		try (Stream<String> stream = Files.lines(path)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			mailbody = buffer.toString();
			mailbody = mailbody.replace(AppConstants.FNAME, user.getFullName());
			mailbody = mailbody.replace(AppConstants.TEMP_PASSWORD, user.getPassword());

		} catch (IOException E) {
			E.printStackTrace();
		}
		return mailbody;
	}

	@Override
	public List<AccountsResponse> viewAllAccounts() {

		List<AccountsResponse> viewAccoutRes = new ArrayList<>();

		List<UserEntity> listOfUsers = userRepository.findAll();

		for (UserEntity users : listOfUsers) {

			if (users.getRole().getRoleName().equals(AppConstants.CASE_WORKER)) {

				AccountsResponse response = new AccountsResponse();
				modelMapper.map(users, response);
				viewAccoutRes.add(response);
			}
		}

		return viewAccoutRes;
	}

	@Override
	public AccountsResponse getAccount(Integer AccountId) {
		Optional<UserEntity> record = userRepository.findById(AccountId);
		if (record.isPresent()) {
			UserEntity userEntity = record.get();

			AccountsResponse response = new AccountsResponse();

			modelMapper.map(userEntity, response);

			return response;
		}
		return null;
	}

	@Override
	public String editContact(Integer AccountId, UserForm userForm) {
		Optional<UserEntity> record = userRepository.findById(AccountId);
		if (record.isPresent()) {
			UserEntity userEntity = record.get();
			userEntity.setEmailId(userForm.getEmailId());
			userEntity.setFullName(userForm.getFullName());
			userEntity.setMobileNumber(userForm.getMobileNumber());
			userEntity.setGender(userForm.getGender());
			userEntity.setSsn(userForm.getSsn());
			userEntity.setDateOfBirth(userForm.getDateOfBirth());
			UserEntity userData = userRepository.save(userEntity);
			if (userData != null) {
				
				Map<String, String> messages = appProperties.getMessages();
				String updateMsg = messages.get(AppConstants.ACCOUNT_UPDATE);
				return updateMsg;
			}

		}
		return null;
	}
	
	@Override
	public void activeSwitch(Integer AccountId) {
		Optional<UserEntity> user = userRepository.findById(AccountId);
		if(user.isPresent()) {
			UserEntity userEntity = user.get();
			boolean status = !(userEntity.isAccSwitch());
			userEntity.setAccSwitch(status);
			userRepository.save(userEntity);
		}
	}
	
	
}