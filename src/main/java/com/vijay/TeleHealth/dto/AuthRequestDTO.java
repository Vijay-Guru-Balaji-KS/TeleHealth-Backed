package com.vijay.TeleHealth.dto;

import jakarta.validation.constraints.NotNull;

public class AuthRequestDTO {

	@NotNull(message = "Identifer (email or phone) cannot be null")
	private String identifier;

	@NotNull(message = "Password cannot be null")
	private String password;

	public AuthRequestDTO() {
	}

	public AuthRequestDTO(@NotNull(message = "Identifer (email or phone) cannot be null") String identifier,
			@NotNull(message = "Password cannot be null") String password) {
		super();
		this.identifier = identifier;
		this.password = password;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequestDTO [identifier=" + identifier + ", password=" + password + "]";
	}

}
