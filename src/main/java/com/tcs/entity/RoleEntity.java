package com.tcs.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ROLE_TBL")
public class RoleEntity {
	
	@Id
	private Integer roleId;
	
	private String roleName;

}
