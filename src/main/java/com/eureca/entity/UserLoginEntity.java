package com.eureca.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Data
@Entity
@Table(name = "User_login")
public class UserLoginEntity {

	@Id
	@GeneratedValue
	private Long Id;

	@Column(name = "UserName", unique = true, updatable = false)
	@Nonnull
	private String userName;

	@Column(name = "PASSWORD")
	@Nonnull
	private String pazzword;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATE_DATE", insertable = true, updatable = false)
	@CreationTimestamp
	private Timestamp createDate;

	@Column(name = "UPDATE_BY", insertable = false)
	private String updateBy;

	@Column(name = "UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private Timestamp updateDate;
}
