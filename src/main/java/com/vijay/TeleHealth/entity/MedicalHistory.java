package com.vijay.TeleHealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_history")
public class MedicalHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String allergies;

	private String previousConditions;

	private String medications;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;

	public MedicalHistory() {
	}

	public MedicalHistory( String allergies, String previousConditions, String medications, User user) {
		super();
		this.allergies = allergies;
		this.previousConditions = previousConditions;
		this.medications = medications;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getPreviousConditions() {
		return previousConditions;
	}

	public void setPreviousConditions(String previousConditions) {
		this.previousConditions = previousConditions;
	}

	public String getMedications() {
		return medications;
	}

	public void setMedications(String medications) {
		this.medications = medications;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "MedicalHistory [id=" + id + ", allergies=" + allergies + ", previousConditions=" + previousConditions
				+ ", medications=" + medications + ", user=" + user + "]";
	}

}
