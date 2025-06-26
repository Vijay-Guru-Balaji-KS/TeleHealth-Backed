package com.vijay.TeleHealth.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ProviderAvailabilityRequestDTO {
	private Long providerId;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;

	public ProviderAvailabilityRequestDTO() {
	}

	public ProviderAvailabilityRequestDTO(Long providerId, LocalDate date, LocalTime startTime, LocalTime endTime) {
		super();
		this.providerId = providerId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
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

	@Override
	public String toString() {
		return "ProviderAvailabilityRequestDTO [providerId=" + providerId + ", date=" + date + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
