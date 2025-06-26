package com.vijay.TeleHealth.dto;

import com.vijay.TeleHealth.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDTO {
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email must be given in proper format")
	private String email;
	private String phoneNumber;
	@NotNull(message = "password cannot be null")
	private String password;
	private RoleType role;

	public RegisterRequestDTO() {
	}

	public RegisterRequestDTO(@NotNull(message = "Name cannot be null") String name,
			@NotNull(message = "Email cannot be null") @Email(message = "Email must be given in proper format") String email,
			String phoneNumber, @NotNull(message = "password cannot be null") String password, RoleType role) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "RegisterRequestDTO [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", password="
				+ password + ", role=" + role + "]";
	}

}
