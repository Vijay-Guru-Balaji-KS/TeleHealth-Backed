package com.vijay.TeleHealth.dto;

public class ProviderProfileResponseDTO {
	private Long id;
	private String bio;
	private String certificates;
	private String profilePhotoUrl;
	private String specialization;
	private ProviderProfessionalDetailsDTO personalDetails;

	public ProviderProfileResponseDTO() {
	}

	public ProviderProfileResponseDTO(Long id, String bio, String certificates, String profilePhotoUrl,
			String specialization, ProviderProfessionalDetailsDTO personalDetails) {
		super();
		this.id = id;
		this.bio = bio;
		this.certificates = certificates;
		this.profilePhotoUrl = profilePhotoUrl;
		this.specialization = specialization;
		this.personalDetails = personalDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCertificates() {
		return certificates;
	}

	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public ProviderProfessionalDetailsDTO getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(ProviderProfessionalDetailsDTO personalDetails) {
		this.personalDetails = personalDetails;
	}

	@Override
	public String toString() {
		return "ProviderProfileResponseDTO [id=" + id + ", bio=" + bio + ", certificates=" + certificates
				+ ", profilePhotoUrl=" + profilePhotoUrl + ", specialization=" + specialization + ", personalDetails="
				+ personalDetails + "]";
	}

}
