package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

	
	
}
