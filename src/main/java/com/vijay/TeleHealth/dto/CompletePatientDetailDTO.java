package com.vijay.TeleHealth.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.vijay.TeleHealth.enums.SlotStatus;

public class CompletePatientDetailDTO {
	private Long id;
	private String gender;
	private LocalDate dateOfBirth;
	private String address;
	private String insuranceProvider;
	private String insuranceNumber;
	private String phoneNumber;
	private String allergies;
	private String previousConditions;
	private String Medications;
	private Float height;
	private Float weight;
	private String bloodPressure;
	private Integer heartRate;
	private Integer fastingLvl;
	private Integer postMealLvl;
	private Integer randomLvl;
	private String patientName;
	private Long slotId;
	private LocalDate appointmentDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private SlotStatus status;
	
	public CompletePatientDetailDTO() {}

	public CompletePatientDetailDTO(Long id, String gender, LocalDate dateOfBirth, String address,
			String insuranceProvider, String insuranceNumber, String phoneNumber, String allergies,
			String previousConditions, String medications, Float height, Float weight, String bloodPressure,
			Integer heartRate, Integer fastingLvl, Integer postMealLvl, Integer randomLvl, String patientName,
			Long slotId, LocalDate appointmentDate, LocalTime startTime, LocalTime endTime, SlotStatus status) {
		super();
		this.id = id;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.insuranceProvider = insuranceProvider;
		this.insuranceNumber = insuranceNumber;
		this.phoneNumber = phoneNumber;
		this.allergies = allergies;
		this.previousConditions = previousConditions;
		Medications = medications;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
		this.fastingLvl = fastingLvl;
		this.postMealLvl = postMealLvl;
		this.randomLvl = randomLvl;
		this.patientName = patientName;
		this.slotId = slotId;
		this.appointmentDate = appointmentDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the dateOfBirth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the insuranceProvider
	 */
	public String getInsuranceProvider() {
		return insuranceProvider;
	}

	/**
	 * @param insuranceProvider the insuranceProvider to set
	 */
	public void setInsuranceProvider(String insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	/**
	 * @return the insuranceNumber
	 */
	public String getInsuranceNumber() {
		return insuranceNumber;
	}

	/**
	 * @param insuranceNumber the insuranceNumber to set
	 */
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
		return Medications;
	}

	/**
	 * @param medications the medications to set
	 */
	public void setMedications(String medications) {
		Medications = medications;
	}

	/**
	 * @return the height
	 */
	public Float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Float height) {
		this.height = height;
	}

	/**
	 * @return the weight
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Float weight) {
		this.weight = weight;
	}

	/**
	 * @return the bloodPressure
	 */
	public String getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @return the heartRate
	 */
	public Integer getHeartRate() {
		return heartRate;
	}

	/**
	 * @param heartRate the heartRate to set
	 */
	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	/**
	 * @return the fastingLvl
	 */
	public Integer getFastingLvl() {
		return fastingLvl;
	}

	/**
	 * @param fastingLvl the fastingLvl to set
	 */
	public void setFastingLvl(Integer fastingLvl) {
		this.fastingLvl = fastingLvl;
	}

	/**
	 * @return the postMealLvl
	 */
	public Integer getPostMealLvl() {
		return postMealLvl;
	}

	/**
	 * @param postMealLvl the postMealLvl to set
	 */
	public void setPostMealLvl(Integer postMealLvl) {
		this.postMealLvl = postMealLvl;
	}

	/**
	 * @return the randomLvl
	 */
	public Integer getRandomLvl() {
		return randomLvl;
	}

	/**
	 * @param randomLvl the randomLvl to set
	 */
	public void setRandomLvl(Integer randomLvl) {
		this.randomLvl = randomLvl;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	 * @return the status
	 */
	public SlotStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(SlotStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CompletePatientDetailDTO [id=" + id + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
				+ ", address=" + address + ", insuranceProvider=" + insuranceProvider + ", insuranceNumber="
				+ insuranceNumber + ", phoneNumber=" + phoneNumber + ", allergies=" + allergies
				+ ", previousConditions=" + previousConditions + ", Medications=" + Medications + ", height=" + height
				+ ", weight=" + weight + ", bloodPressure=" + bloodPressure + ", heartRate=" + heartRate
				+ ", fastingLvl=" + fastingLvl + ", postMealLvl=" + postMealLvl + ", randomLvl=" + randomLvl
				+ ", patientName=" + patientName + ", slotId=" + slotId + ", appointmentDate=" + appointmentDate
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + "]";
	}

}
