package com.vijay.TeleHealth.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.vijay.TeleHealth.enums.SlotStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "provider_availability")
public class ProviderAvailability {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "provider_id", nullable = false)
	private User provider;

	private LocalDate date;

	private LocalTime startTime;

	private LocalTime endTime;

	@Column(columnDefinition = "TINYINT(1)")
	private Boolean isBooked = false;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SlotStatus slotStatus = SlotStatus.AVAILABLE;

	public ProviderAvailability() {
	}

	public ProviderAvailability(Long id, User provider, LocalDate date, LocalTime startTime, LocalTime endTime,
			Boolean isBooked, SlotStatus slotStatus) {
		super();
		this.id = id;
		this.provider = provider;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isBooked = isBooked;
		this.slotStatus = slotStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getProvider() {
		return provider;
	}

	public void setProvider(User provider) {
		this.provider = provider;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsBooked() {
		return isBooked;
	}

	public void setIsBooked(Boolean isBooked) {
		this.isBooked = isBooked;
	}

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	@Override
	public String toString() {
		return "ProviderAvailability [id=" + id + ", provider=" + provider + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + ", isBooked=" + isBooked + ", slotStatus=" + slotStatus + "]";
	}

}
