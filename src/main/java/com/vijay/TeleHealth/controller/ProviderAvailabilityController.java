package com.vijay.TeleHealth.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.TeleHealth.dto.ApiResponse;
import com.vijay.TeleHealth.dto.PatientSideDocInfoDTO;
import com.vijay.TeleHealth.dto.ProviderAvailabilityRequestDTO;
import com.vijay.TeleHealth.dto.ProviderAvailabilityResponseDTO;
import com.vijay.TeleHealth.dto.ProviderSidePatientInfoDTO;
import com.vijay.TeleHealth.dto.UserDTO;
import com.vijay.TeleHealth.exception.HealthMetricException;
import com.vijay.TeleHealth.exception.MedicalHistoryException;
import com.vijay.TeleHealth.exception.PatientBookingException;
import com.vijay.TeleHealth.exception.ProfileDetailsException;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.service.ProviderAvailabilityServiceImpl;

@RestController
@RequestMapping("/api/availability")
public class ProviderAvailabilityController {

	@Autowired
	private ProviderAvailabilityServiceImpl availabilityService;

	@GetMapping("/provider")
	public ResponseEntity<List<UserDTO>> getAllVendors() {
		return new ResponseEntity<List<UserDTO>>(availabilityService.getAllProviders(), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addAvailability(@RequestBody ProviderAvailabilityRequestDTO req)
			throws ProviderAvailabilityException {
		availabilityService.addAvailability(req);
		return ResponseEntity.ok(new ApiResponse("Availability added successfully"));
	}

	@GetMapping("/slots")
	public ResponseEntity<List<ProviderAvailabilityResponseDTO>> getSlotsBasedOnDate(
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		return new ResponseEntity<List<ProviderAvailabilityResponseDTO>>(availabilityService.showSlotsBasedOnDate(date),
				HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<List<PatientSideDocInfoDTO>> getAllDetails(
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		return new ResponseEntity<List<PatientSideDocInfoDTO>>(availabilityService.getAllDetailsAboutDoc(date),
				HttpStatus.OK);
	}

	@GetMapping("/slots/personal")
	public ResponseEntity<List<ProviderAvailabilityResponseDTO>> getAvailableSlots(@RequestParam Long providerId,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate date) {
		return new ResponseEntity<List<ProviderAvailabilityResponseDTO>>(
				availabilityService.getAvailableSlots(providerId, date), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{providerId}/{slotId}")
	public ResponseEntity<ApiResponse> deleteSlot(@PathVariable Long providerId, @PathVariable Long slotId)
			throws ProviderAvailabilityException {
		availabilityService.deleteSlot(providerId, slotId);
		return ResponseEntity.ok(new ApiResponse("slot deleted successfully"));
	}

	@PostMapping("/update/{providerId}/{slotId}")
	public ResponseEntity<ApiResponse> updateSlot(@PathVariable Long providerId, @PathVariable Long slotId,
			@RequestBody ProviderAvailabilityRequestDTO req) throws ProviderAvailabilityException {
		availabilityService.updateSlot(providerId, slotId, req);
		return ResponseEntity.ok(new ApiResponse("slot updated successfully"));
	}

	@PatchMapping("/book/{providerId}/{patientId}/{slotId}")
	public ResponseEntity<ProviderSidePatientInfoDTO> bookSlot(@PathVariable Long providerId,
			@PathVariable Long patientId, @PathVariable Long slotId)
			throws PatientBookingException, MedicalHistoryException, HealthMetricException, ProfileDetailsException {
		ProviderSidePatientInfoDTO dto = availabilityService.updateSlotStatusAndGetPatientDetails(patientId, slotId,
				providerId);
		return new ResponseEntity<ProviderSidePatientInfoDTO>(dto, HttpStatus.OK);
	}
}
