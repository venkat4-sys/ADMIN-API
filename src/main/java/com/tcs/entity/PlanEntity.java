package com.tcs.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="PLAN_TBL")
public class PlanEntity {
	
	@Id
	private Integer planId;
	
	private String planName;
	
	private String planCategory;
	
	private boolean accSwitch;
	
	private Date planStartDate;
	
	private Date planEndDate;
	
	@Column(name="PLAN_CREATED_DATE",updatable = false)
	private Date planCreatedDate;
	
	@Column(name="PLAN_UPDATED_DATE",insertable = false)
	private Date planUpdatedDate;
	
	@ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

}
