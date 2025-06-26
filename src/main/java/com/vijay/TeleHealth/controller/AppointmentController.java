package com.vijay.TeleHealth.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.TeleHealth.dto.ApiResponse;
import com.vijay.TeleHealth.dto.CompletePatientDetailDTO;
import com.vijay.TeleHealth.dto.PartialDocInfoForPatientDTO;
import com.vijay.TeleHealth.dto.PrescriptionResponseDTO;
import com.vijay.TeleHealth.entity.DiabeticInfo;
import com.vijay.TeleHealth.enums.AppointmentStatus;
import com.vijay.TeleHealth.exception.AppointmentException;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.repository.DiabeticInfoRepository;
import com.vijay.TeleHealth.service.AppointmentServiceImpl;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	@Autowired
	AppointmentServiceImpl appointmentService;

	@Autowired
	DiabeticInfoRepository diabeticInfoRepository;

	@PostMapping("/book")
	public ResponseEntity<ApiResponse> bookSlot(@RequestParam LocalDate date, @RequestParam Long patientId,
			@RequestParam Long providerId, @RequestParam Long slotId)
			throws CustomException, ProviderAvailabilityException {
		ApiResponse res = appointmentService.patientBookSlot(date, patientId, providerId, slotId);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	@GetMapping("/updatestatus")
	public ResponseEntity<ApiResponse> approveOrRejectSlot(@RequestParam Long slotId,
			@RequestParam AppointmentStatus status) throws AppointmentException, ProviderAvailabilityException {
		ApiResponse res = appointmentService.ApproveOrRejectSlot(slotId, status);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<CompletePatientDetailDTO>> getAllAppointments(@RequestParam Long providerId) {
		List<CompletePatientDetailDTO> app = appointmentService.getAllAppointmentsForProvider(providerId);
		return ResponseEntity.ok(app);
	}

	@PostMapping(value = "/followup/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ApiResponse> submitFollowUpDetails(@RequestParam Long appointmentId,
			@RequestParam String dietAdvice, @RequestParam String exerciseSuggestion,
			@RequestPart(required = false) MultipartFile prescriptionFile, @RequestParam String notes)
			throws IOException, AppointmentException {
		ApiResponse res = appointmentService.submitFollowUpDetails(appointmentId, dietAdvice, exerciseSuggestion,
				prescriptionFile, notes);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/getPrescription")
	public ResponseEntity<PrescriptionResponseDTO> getPrescription(@RequestParam Long appointmentId)
			throws AppointmentException {
		return new ResponseEntity<PrescriptionResponseDTO>(appointmentService.getPrescriptionDetails(appointmentId),
				HttpStatus.OK);
	}

	@GetMapping("/patient/{patientId}")
	public ResponseEntity<List<PartialDocInfoForPatientDTO>> getDetails(
			@PathVariable(name = "patientId") Long patientId) {
		List<PartialDocInfoForPatientDTO> app = appointmentService.getAllPatientSlots(patientId);
		return ResponseEntity.ok(app);
	}

	@DeleteMapping("/cancel/{slotId}")
	public ResponseEntity<String> cancelAppointment(@PathVariable Long slotId)
			throws ProviderAvailabilityException, AppointmentException {
		boolean s = appointmentService.cancelAppointmentById(slotId);
		if (s)
			return ResponseEntity.ok("Appointment cancelled successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("appointment not found");
	}

	@GetMapping("/diabetic-info")
	public List<DiabeticInfo> getDiabeticInfoByPatient(@RequestParam Long patientId) {
		List<PartialDocInfoForPatientDTO> app = appointmentService.getAllPatientSlots(patientId);
		return diabeticInfoRepository.findByPatientIdOrderByUpdatedOnAsc(patientId);
	}
}
