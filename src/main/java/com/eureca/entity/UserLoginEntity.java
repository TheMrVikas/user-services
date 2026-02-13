package com.eureca.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "User_login")
public class UserLoginEntity extends AuditableEntity {

	@Id
	@GeneratedValue
	private Long Id;

	@Column(name = "UserName", unique = true, updatable = false)
	@Nonnull
	private String userName;

	@Column(name = "PASSWORD")
	@Nonnull
	private String pazzword;
}
