package com.vijay.TeleHealth.dto;

public class AppointmentRequestDTO {
	private Long patientId;
	private Long providerId;
	private Long slotId;
	private String notes;

	public AppointmentRequestDTO() {
	}

	public AppointmentRequestDTO(Long patientId, Long providerId, Long slotId, String notes) {
		super();
		this.patientId = patientId;
		this.providerId = providerId;
		this.slotId = slotId;
		this.notes = notes;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AppointmentRequestDTO [patientId=" + patientId + ", providerId=" + providerId + ", slotId=" + slotId
				+ ", notes=" + notes + "]";
	}

}
