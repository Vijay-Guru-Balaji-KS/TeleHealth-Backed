package com.vijay.TeleHealth.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient_profiles")
public class PatientProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;

	private String gender;

	private LocalDate dateOfBirth;

	private String address;

	private String insuranceProvider;

	private String insuranceNumber;

	private String phoneNumber;

	public PatientProfile() {
	}

	public PatientProfile(Long id, User user, String gender, LocalDate dateOfBirth, String address,
			String insuranceProvider, String insuranceNumber, String phoneNumber) {
		super();
		this.id = id;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		return "PatientProfile [id=" + id + ", user=" + user + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + ", insuranceProvider=" + insuranceProvider + ", insuranceNumber="
				+ insuranceNumber + ", phoneNumber=" + phoneNumber + "]";
	}

}
