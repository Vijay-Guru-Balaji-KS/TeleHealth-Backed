package com.vijay.TeleHealth.dto;

public class ProviderProfessionalDetailsDTO {

	private String name;
	private String email;
	private String phoneNumber;

	public ProviderProfessionalDetailsDTO(String name, String email, String phoneNumber) {
		super();
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
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

	@Override
	public String toString() {
		return "ProviderProfessionalDetailsDTO [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ "]";
	}

}
