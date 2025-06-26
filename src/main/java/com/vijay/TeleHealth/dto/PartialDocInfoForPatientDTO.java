package com.vijay.TeleHealth.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.vijay.TeleHealth.enums.AppointmentStatus;

public class PartialDocInfoForPatientDTO {
	private Long slotId;
	private String docName;
	private LocalDate appointmentDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private AppointmentStatus appointmentStatus;
	private String prescriptionPath;
	private String diet = "null";
	private String workOut = "null";
	private Long docId;
	private String notes = "null";

	public PartialDocInfoForPatientDTO() {
	}

	public PartialDocInfoForPatientDTO(Long slotId, String docName, LocalDate appointmentDate, LocalTime startTime,
			LocalTime endTime, AppointmentStatus appointmentStatus, String prescriptionPath, String diet,
			String workOut, Long docId, String notes) {
		super();
		this.slotId = slotId;
		this.docName = docName;
		this.appointmentDate = appointmentDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.appointmentStatus = appointmentStatus;
		this.prescriptionPath = prescriptionPath;
		this.diet = diet;
		this.workOut = workOut;
		this.docId = docId;
		this.notes = notes;
	}

	/**
	 * @return the slotId
	 */
	public Long getSlotId() {
		return slotId;
	}

	/**
	 * @param slotId the slotId to set
	 */
	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @param docName the docName to set
	 */
	public void setDocName(String docName) {
		this.docName = docName;
	}

	/**
	 * @return the appointmentDate
	 */
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the appointmentStatus
	 */
	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	/**
	 * @param appointmentStatus the appointmentStatus to set
	 */
	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	/**
	 * @return the prescriptionPath
	 */
	public String getPrescriptionPath() {
		return prescriptionPath;
	}

	/**
	 * @param prescriptionPath the prescriptionPath to set
	 */
	public void setPrescriptionPath(String prescriptionPath) {
		this.prescriptionPath = prescriptionPath;
	}

	/**
	 * @return the diet
	 */
	public String getDiet() {
		return diet;
	}

	/**
	 * @param diet the diet to set
	 */
	public void setDiet(String diet) {
		this.diet = diet;
	}

	/**
	 * @return the workOut
	 */
	public String getWorkOut() {
		return workOut;
	}

	/**
	 * @param workOut the workOut to set
	 */
	public void setWorkOut(String workOut) {
		this.workOut = workOut;
	}

	/**
	 * @return the docId
	 */
	public Long getDocId() {
		return docId;
	}

	/**
	 * @param docId the docId to set
	 */
	public void setDocId(Long docId) {
		this.docId = docId;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "PartialDocInfoForPatientDTO [slotId=" + slotId + ", docName=" + docName + ", appointmentDate="
				+ appointmentDate + ", startTime=" + startTime + ", endTime=" + endTime + ", appointmentStatus="
				+ appointmentStatus + ", prescriptionPath=" + prescriptionPath + ", diet=" + diet + ", workOut="
				+ workOut + ", docId=" + docId + ", notes=" + notes + "]";
	}

}
