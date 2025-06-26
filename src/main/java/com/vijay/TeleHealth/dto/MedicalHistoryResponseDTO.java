package com.vijay.TeleHealth.dto;

public class MedicalHistoryResponseDTO {
	private String allergies;
	private String previousConditions;
	private String medications;

	public MedicalHistoryResponseDTO() {
	}

	public MedicalHistoryResponseDTO(String allergies, String previousConditions, String medications) {
		super();
		this.allergies = allergies;
		this.previousConditions = previousConditions;
		this.medications = medications;
	}

	/**
	 * @return the allergies
	 */
	public String getAllergies() {
		return allergies;
	}

	/**
	 * @param allergies the allergies to set
	 */
	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	/**
	 * @return the previousConditions
	 */
	public String getPreviousConditions() {
		return previousConditions;
	}

	/**
	 * @param previousConditions the previousConditions to set
	 */
	public void setPreviousConditions(String previousConditions) {
		this.previousConditions = previousConditions;
	}

	/**
	 * @return the medications
	 */
	public String getMedications() {
		return medications;
	}

	/**
	 * @param medications the medications to set
	 */
	public void setMedications(String medications) {
		this.medications = medications;
	}

	@Override
	public String toString() {
		return "MedicalHistoryResponseDTO [allergies=" + allergies + ", previousConditions=" + previousConditions
				+ ", medications=" + medications + "]";
	}

}
