package com.vijay.TeleHealth.dto;

public class HealthMetricRequestDTO {

	private Long id;
	private Float height;
	private Float weight;
	private String bloodPressure;
	private Integer fastingLvl;
	private Integer postMealLvl;
	private Integer randomLvl;
	private Integer heartRate;

	public HealthMetricRequestDTO() {
	}

	public HealthMetricRequestDTO(Long id, Float height, Float weight, String bloodPressure, Integer fastingLvl,
			Integer postMealLvl, Integer randomLvl, Integer heartRate) {
		super();
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.fastingLvl = fastingLvl;
		this.postMealLvl = postMealLvl;
		this.randomLvl = randomLvl;
		this.heartRate = heartRate;
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

	@Override
	public String toString() {
		return "HealthMetricRequestDTO [id=" + id + ", height=" + height + ", weight=" + weight + ", bloodPressure="
				+ bloodPressure + ", fastingLvl=" + fastingLvl + ", postMealLvl=" + postMealLvl + ", randomLvl="
				+ randomLvl + ", heartRate=" + heartRate + "]";
	}

}
