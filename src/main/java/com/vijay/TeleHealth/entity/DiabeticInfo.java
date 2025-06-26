package com.vijay.TeleHealth.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DiabeticInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer fastingLvl;
	
	private Integer postMealLvl;
	
	private Integer randomLvl;
	
	private LocalDate updatedOn;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "patient_id", referencedColumnName = "id",nullable = false)
	private User patient;
	
	public DiabeticInfo() {}

	public DiabeticInfo(Long id, Integer fastingLvl, Integer postMealLvl, Integer randomLvl, LocalDate updatedOn,
			User patient) {
		super();
		this.id = id;
		this.fastingLvl = fastingLvl;
		this.postMealLvl = postMealLvl;
		this.randomLvl = randomLvl;
		this.updatedOn = updatedOn;
		this.patient = patient;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFastingLvl() {
		return fastingLvl;
	}

	public void setFastingLvl(Integer fastingLvl) {
		this.fastingLvl = fastingLvl;
	}

	public Integer getPostMealLvl() {
		return postMealLvl;
	}

	public void setPostMealLvl(Integer postMealLvl) {
		this.postMealLvl = postMealLvl;
	}

	public Integer getRandomLvl() {
		return randomLvl;
	}

	public void setRandomLvl(Integer randomLvl) {
		this.randomLvl = randomLvl;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "DiabeticInfo [id=" + id + ", fastingLvl=" + fastingLvl + ", postMealLvl=" + postMealLvl + ", randomLvl="
				+ randomLvl + ", updatedOn=" + updatedOn + ", patient=" + patient + "]";
	}
	
	
}
