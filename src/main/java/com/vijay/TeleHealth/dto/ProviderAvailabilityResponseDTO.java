package com.vijay.TeleHealth.dto;

import java.time.LocalTime;

import com.vijay.TeleHealth.enums.SlotStatus;

public class ProviderAvailabilityResponseDTO {
	private Long id;
	private LocalTime startTime;
	private LocalTime endTime;
	private SlotStatus slotStatus;

	private Long providerId;

	public ProviderAvailabilityResponseDTO(Long id, LocalTime startTime, LocalTime endTime, SlotStatus slotStatus,
			Long providerId) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.slotStatus = slotStatus;
		this.providerId = providerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public SlotStatus getSlotStatus() {
		return slotStatus;
	}

	public void setSlotStatus(SlotStatus slotStatus) {
		this.slotStatus = slotStatus;
	}

	public Long getProviderId() {
		return providerId;
	}

	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}

	@Override
	public String toString() {
		return "ProviderAvailabilityResponseDTO [id=" + id + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", slotStatus=" + slotStatus + ", providerId=" + providerId + "]";
	}

}
