package com.vijay.TeleHealth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sugar_details")
public class SugarLevels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer fastingLvl;

	private Integer postMealLvl;

	private Integer randomLvl;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
	private User user;

	public SugarLevels() {
	}

	public SugarLevels(Long id, Integer fastingLvl, Integer postMealLvl, Integer randomLvl, User user) {
		super();
		this.id = id;
		this.fastingLvl = fastingLvl;
		this.postMealLvl = postMealLvl;
		this.randomLvl = randomLvl;
		this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SugarLevels [id=" + id + ", fastingLvl=" + fastingLvl + ", postMealLvl=" + postMealLvl + ", randomLvl="
				+ randomLvl + ", user=" + user + "]";
	}

}
