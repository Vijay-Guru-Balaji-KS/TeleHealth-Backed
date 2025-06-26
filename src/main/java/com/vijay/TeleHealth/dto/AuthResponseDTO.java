package com.vijay.TeleHealth.dto;

import com.vijay.TeleHealth.enums.RoleType;

public class AuthResponseDTO {
	private Long id;
	private String email;
	private String message;
	private RoleType role;
	
	public AuthResponseDTO() {}

	public AuthResponseDTO(Long id, String email, String message, RoleType role) {
		super();
		this.id = id;
		this.email = email;
		this.message = message;
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the role
	 */
	public RoleType getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(RoleType role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AuthResponseDTO [id=" + id + ", email=" + email + ", message=" + message + ", role=" + role + "]";
	}
	
	
}
