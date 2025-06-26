package com.vijay.TeleHealth.dto;

import java.util.List;

public class PatientSideDocInfoDTO {
	private Long provider_id;
	private String docName;
	private String docEmail;
	private String phNo;
	private String bio;
	private String specialization;
	private String certificationsDetails;
	private String profilePhotoURL;
	private List<ProviderAvailabilityResponseDTO> docSlots;

	public PatientSideDocInfoDTO(Long provider_id, String docName, String docEmail, String phNo, String bio,
			String specialization, String certificationsDetails, String profilePhotoURL,
			List<ProviderAvailabilityResponseDTO> docSlots) {
		super();
		this.provider_id = provider_id;
		this.docName = docName;
		this.docEmail = docEmail;
		this.phNo = phNo;
		this.bio = bio;
		this.specialization = specialization;
		this.certificationsDetails = certificationsDetails;
		this.profilePhotoURL = profilePhotoURL;
		this.docSlots = docSlots;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocEmail() {
		return docEmail;
	}

	public void setDocEmail(String docEmail) {
		this.docEmail = docEmail;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getCertificationsDetails() {
		return certificationsDetails;
	}

	public void setCertificationsDetails(String certificationsDetails) {
		this.certificationsDetails = certificationsDetails;
	}

	public String getProfilePhotoURL() {
		return profilePhotoURL;
	}

	public void setProfilePhotoURL(String profilePhotoURL) {
		this.profilePhotoURL = profilePhotoURL;
	}

	public List<ProviderAvailabilityResponseDTO> getDocSlots() {
		return docSlots;
	}

	public void setDocSlots(List<ProviderAvailabilityResponseDTO> docSlots) {
		this.docSlots = docSlots;
	}

	@Override
	public String toString() {
		return "PatientSideDocInfoDTO [provider_id=" + provider_id + ", docName=" + docName + ", docEmail=" + docEmail
				+ ", phNo=" + phNo + ", bio=" + bio + ", specialization=" + specialization + ", certificationsDetails="
				+ certificationsDetails + ", profilePhotoURL=" + profilePhotoURL + ", docSlots=" + docSlots + "]";
	}

}
