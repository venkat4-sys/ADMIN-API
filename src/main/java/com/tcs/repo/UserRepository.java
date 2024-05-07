package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	UserEntity findByEmailId(String email);
	
	UserEntity findByPassword(String password);
	
	
	UserEntity findByEmailIdAndPassword(String email,String password);

}
