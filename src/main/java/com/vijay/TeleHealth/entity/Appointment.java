package com.vijay.TeleHealth.entity;

import java.time.LocalDate;

import com.vijay.TeleHealth.enums.AppointmentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	@Enumerated(value = EnumType.STRING)
	private AppointmentStatus status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id", nullable = false, referencedColumnName = "id")
	private User patient;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "provider_id", nullable = false, referencedColumnName = "id")
	private User provider;

	private Boolean is_approved = false;

	private Long slot_id;

	@Column(length = 1000)
	private String dietAdvice;

	@Column(length = 1000)
	private String exerciseSuggestion;

	private String prescriptionFilePath;

	private String notes;

	public Appointment() {
	}

	public Appointment(LocalDate date, AppointmentStatus status, User patient, User provider,
			Boolean is_approved, Long slot_id, String dietAdvice, String exerciseSuggestion,
			String prescriptionFilePath, String notes) {
		super();
		this.date = date;
		this.status = status;
		this.patient = patient;
		this.provider = provider;
		this.is_approved = is_approved;
		this.slot_id = slot_id;
		this.dietAdvice = dietAdvice;
		this.exerciseSuggestion = exerciseSuggestion;
		this.prescriptionFilePath = prescriptionFilePath;
		this.notes = notes;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public User getProvider() {
		return provider;
	}

	public void setProvider(User provider) {
		this.provider = provider;
	}

	public Boolean getIs_approved() {
		return is_approved;
	}

	public void setIs_approved(Boolean is_approved) {
		this.is_approved = is_approved;
	}

	public Long getSlot_id() {
		return slot_id;
	}

	public void setSlot_id(Long slot_id) {
		this.slot_id = slot_id;
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

	public String getPrescriptionFilePath() {
		return prescriptionFilePath;
	}

	public void setPrescriptionFilePath(String prescriptionFilePath) {
		this.prescriptionFilePath = prescriptionFilePath;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", status=" + status + ", patient=" + patient
				+ ", provider=" + provider + ", is_approved=" + is_approved + ", slot_id=" + slot_id + ", dietAdvice="
				+ dietAdvice + ", exerciseSuggestion=" + exerciseSuggestion + ", prescriptionFilePath="
				+ prescriptionFilePath + ", notes=" + notes + "]";
	}

}
