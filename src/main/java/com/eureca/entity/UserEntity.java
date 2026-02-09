package com.eureca.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true)
	private String email;

	//private String password;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "country")
	private String country;

	//@Column(name = "profile_picture_url")
	//private String profilePictureUrl;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_Date", updatable = false)
	@CreationTimestamp
	private Timestamp createdAt;

	@Column(name = "updated_Date", insertable = false)
	@UpdateTimestamp
	private Timestamp updatedAt;
}

/*private String role;
private String lastLoginAt;
private String passwordResetToken;
private String passwordResetTokenExpiry;
private String emailVerificationToken;
private String emailVerificationTokenExpiry;
private String twoFactorAuthSecret;
private boolean isTwoFactorAuthEnabled;
private String preferredLanguage;
private String preferredCurrency;
private String timeZone;
private String dateOfBirth;
*/
