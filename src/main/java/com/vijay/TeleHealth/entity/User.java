package com.vijay.TeleHealth.entity;

import java.time.LocalDateTime;

import com.vijay.TeleHealth.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true, length = 50)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, unique = true)
	private String phoneNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RoleType role;

	private LocalDateTime createdAt = LocalDateTime.now();

	private LocalDateTime updatedAt = LocalDateTime.now();

	@Column(name = "enabled")
	private Boolean enabled = false;

	public User() {
	}

	public User(Long id, String name, String email, String password, String phoneNumber, RoleType role,
			LocalDateTime createdAt, LocalDateTime updatedAt, Boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + ", role=" + role + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", enabled=" + enabled + "]";
	}

}
