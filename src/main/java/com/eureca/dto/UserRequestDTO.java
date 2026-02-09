package com.eureca.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	private String isActive;
}
