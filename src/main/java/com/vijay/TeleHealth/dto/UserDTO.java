package com.vijay.TeleHealth.dto;

import com.vijay.TeleHealth.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserDTO {

	private Long id;
	@NotNull(message = "Name cannot be null")
	private String name;

	@NotNull(message = "Email cannot be null")
	@Email(message = "Email must be given in proper format")
	private String email;
	@NotNull(message = "Password cannot be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*\\\\d)(?=.*[^a-zA-Z\\\\d).{8,}$")
	private String password;
	@NotNull(message = "Phone number is a required field")
	@Pattern(regexp = "^[6-9]//d{9}$")
	private String phoneNumber;
	private RoleType role;

	public UserDTO(Long id, @NotNull(message = "Name cannot be null") String name,
			@NotNull(message = "Email cannot be null") @Email(message = "Email must be given in proper format") String email,
			@NotNull(message = "Password cannot be null") @Pattern(regexp = "^(?=.*[a-z])(?=.*\\\\d)(?=.*[^a-zA-Z\\\\d).{8,}$") String password,
			@NotNull(message = "Phone number is a required field") @Pattern(regexp = "^[6-9]//d{9}$") String phoneNumber,
			RoleType role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", role=" + role + "]";
	}

}
