package com.vijay.TeleHealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_metrics")
public class HealthMetrics {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Float height;

	private Float weight;

	private String bloodPressure;

	private Integer heartRate;

	@OneToOne
	@JoinColumn(referencedColumnName = "id", name = "sugar_id")
	private SugarLevels sugar;

	public HealthMetrics() {

	}

	public HealthMetrics( Float height, Float weight, String bloodPressure, Integer heartRate,
			SugarLevels sugar) {
		super();
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
		this.sugar = sugar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getHeight() {
		return height;
	}

	public void setHeight(Float height) {
		this.height = height;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public Integer getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(Integer heartRate) {
		this.heartRate = heartRate;
	}

	public SugarLevels getSugar() {
		return sugar;
	}

	public void setSugar(SugarLevels sugar) {
		this.sugar = sugar;
	}

	@Override
	public String toString() {
		return "HealthMetrics [id=" + id + ", height=" + height + ", weight=" + weight + ", bloodPressure="
				+ bloodPressure + ", heartRate=" + heartRate + ", sugar=" + sugar + "]";
	}

}
