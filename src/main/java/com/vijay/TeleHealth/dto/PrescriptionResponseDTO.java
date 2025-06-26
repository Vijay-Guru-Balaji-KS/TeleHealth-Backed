package com.vijay.TeleHealth.dto;

public class PrescriptionResponseDTO {

	private Long appointmentId;
	private String dietAdvice;
	private String exerciseSuggestion;
	private String prescriptionPath;
	private Long providerId;
	private String notes;

	public PrescriptionResponseDTO() {
	}

	public PrescriptionResponseDTO(Long appointmentId, String dietAdvice, String exerciseSuggestion,
			String prescriptionPath, Long providerId, String notes) {
		super();
		this.appointmentId = appointmentId;
		this.dietAdvice = dietAdvice;
		this.exerciseSuggestion = exerciseSuggestion;
		this.prescriptionPath = prescriptionPath;
		this.providerId = providerId;
		this.notes = notes;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDietAdvice() {
		return dietAdvice;
	}

	public void setDietAdvice(String dietAdvice) {
		this.dietAdvice = dietAdvice;
	}

	public String getExerciseSuggestion() {
		return exerciseSuggestion;
	}

	public void setExerciseSuggestion(String exerciseSuggestion) {
		this.exerciseSuggestion = exerciseSuggestion;
	}

	public String getPrescriptionPath() {
		return prescriptionPath;
	}

	public void setPrescriptionPath(String prescriptionPath) {
		this.prescriptionPath = prescriptionPath;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "PrescriptionResponseDTO [appointmentId=" + appointmentId + ", dietAdvice=" + dietAdvice
				+ ", exerciseSuggestion=" + exerciseSuggestion + ", prescriptionPath=" + prescriptionPath
				+ ", providerId=" + providerId + ", notes=" + notes + "]";
	}

}
