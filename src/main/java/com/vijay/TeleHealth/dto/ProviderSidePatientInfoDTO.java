package com.vijay.TeleHealth.dto;

public class ProviderSidePatientInfoDTO {
	private Long patientId;
	private Long slotId;
	private PatientProfileResponseDTO patientProfile;
	private HealthMetricResponseDTO healthMetric;
	private MedicalHistoryResponseDTO medicalHistory;
	private ProviderAvailabilityResponseDTO providerAvailabilityResponse;

	public ProviderSidePatientInfoDTO() {
	}

	public ProviderSidePatientInfoDTO(Long patientId, Long slotId, PatientProfileResponseDTO patientProfile,
			HealthMetricResponseDTO healthMetric, MedicalHistoryResponseDTO medicalHistory,
			ProviderAvailabilityResponseDTO providerAvailabilityResponse) {
		super();
		this.patientId = patientId;
		this.slotId = slotId;
		this.patientProfile = patientProfile;
		this.healthMetric = healthMetric;
		this.medicalHistory = medicalHistory;
		this.providerAvailabilityResponse = providerAvailabilityResponse;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public PatientProfileResponseDTO getPatientProfile() {
		return patientProfile;
	}

	public void setPatientProfile(PatientProfileResponseDTO patientProfile) {
		this.patientProfile = patientProfile;
	}

	public HealthMetricResponseDTO getHealthMetric() {
		return healthMetric;
	}

	public void setHealthMetric(HealthMetricResponseDTO healthMetric) {
		this.healthMetric = healthMetric;
	}

	public MedicalHistoryResponseDTO getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(MedicalHistoryResponseDTO medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public ProviderAvailabilityResponseDTO getProviderAvailabilityResponse() {
		return providerAvailabilityResponse;
	}

	public void setProviderAvailabilityResponse(ProviderAvailabilityResponseDTO providerAvailabilityResponse) {
		this.providerAvailabilityResponse = providerAvailabilityResponse;
	}

	@Override
	public String toString() {
		return "ProviderSidePatientInfoDTO [patientId=" + patientId + ", slotId=" + slotId + ", patientProfile="
				+ patientProfile + ", healthMetric=" + healthMetric + ", medicalHistory=" + medicalHistory
				+ ", providerAvailabilityResponse=" + providerAvailabilityResponse + "]";
	}

}
