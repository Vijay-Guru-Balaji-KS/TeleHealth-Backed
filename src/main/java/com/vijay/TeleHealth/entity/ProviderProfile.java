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
@Table(name = "provider_profiles")
public class ProviderProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;

	private String specialization;

	private String certificationDetails;

	private String profilePhotoPath;

	private String bio;

	public ProviderProfile() {
	}

	public ProviderProfile(User user, String specialization, String certificationDetails, String profilePhotoPath,
			String bio) {
		super();
		this.user = user;
		this.specialization = specialization;
		this.certificationDetails = certificationDetails;
		this.profilePhotoPath = profilePhotoPath;
		this.bio = bio;
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getCertificationDetails() {
		return certificationDetails;
	}

	public void setCertificationDetails(String certificationDetails) {
		this.certificationDetails = certificationDetails;
	}

	public String getProfilePhotoPath() {
		return profilePhotoPath;
	}

	public void setProfilePhotoPath(String profilePhotoPath) {
		this.profilePhotoPath = profilePhotoPath;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "ProviderProfile [id=" + id + ", user=" + user + ", specialization=" + specialization
				+ ", certificationDetails=" + certificationDetails + ", profilePhotoPath=" + profilePhotoPath + ", bio="
				+ bio + "]";
	}

}
