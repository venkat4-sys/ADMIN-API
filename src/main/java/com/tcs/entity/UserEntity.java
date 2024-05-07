package com.tcs.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USER_TBL")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	private String fullName;

	private String emailId;

	private Long mobileNumber;

	private String gender;

	private Date dateOfBirth;

	private String ssn;

	private String status;

	private boolean AccSwitch;

	private String password;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private Date updatedDate;

	@Column(name = "CREATED_BY")
	private Integer createdBy;

	@Column(name = "UPDATED_BY")
	private Integer updatedBy;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roleId")
	private RoleEntity role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PlanEntity> plans;

}
