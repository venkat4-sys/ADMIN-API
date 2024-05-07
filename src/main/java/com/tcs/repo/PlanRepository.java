package com.tcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.PlanEntity;

public interface PlanRepository extends JpaRepository<PlanEntity, Integer>{

}
