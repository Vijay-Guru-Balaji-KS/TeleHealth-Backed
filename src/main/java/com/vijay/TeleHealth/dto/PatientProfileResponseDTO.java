package com.vijay.TeleHealth.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientProfileResponseDTO {
	private Long id;
	private String name;
	private String email;
	private String gender;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private String address;
	private String insuranceProvider;
	private String insuranceNumber;
	private String phoneNumber;

	public PatientProfileResponseDTO() {
	}

	public PatientProfileResponseDTO(Long id, String name, String email, String gender, LocalDate dateOfBirth,
			String address, String insuranceProvider, String insuranceNumber, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.insuranceProvider = insuranceProvider;
		this.insuranceNumber = insuranceNumber;
		this.phoneNumber = phoneNumber;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "PatientProfileResponseDTO [id=" + id + ", name=" + name + ", email=" + email + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", insuranceProvider=" + insuranceProvider
				+ ", insuranceNumber=" + insuranceNumber + ", phoneNumber=" + phoneNumber + "]";
	}

}
