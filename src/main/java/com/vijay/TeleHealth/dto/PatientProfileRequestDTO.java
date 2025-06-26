package com.vijay.TeleHealth.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PatientProfileRequestDTO {
	private String gender;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	private String address;
	private String insuranceProvider;
	private String insuranceNumber;

	public PatientProfileRequestDTO() {
	}

	public PatientProfileRequestDTO(String gender, LocalDate dateOfBirth, String address, String insuranceProvider,
			String insuranceNumber) {
		super();
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.insuranceProvider = insuranceProvider;
		this.insuranceNumber = insuranceNumber;
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

	@Override
	public String toString() {
		return "PatientProfileRequestDTO [gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", address=" + address
				+ ", insuranceProvider=" + insuranceProvider + ", insuranceNumber=" + insuranceNumber + "]";
	}

}
