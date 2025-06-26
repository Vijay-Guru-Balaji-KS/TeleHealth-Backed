package com.vijay.TeleHealth.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.TeleHealth.dto.HealthMetricRequestDTO;
import com.vijay.TeleHealth.dto.HealthMetricResponseDTO;
import com.vijay.TeleHealth.dto.PatientProfileRequestDTO;
import com.vijay.TeleHealth.dto.PatientProfileResponseDTO;
import com.vijay.TeleHealth.entity.ChatMessage;
import com.vijay.TeleHealth.entity.DiabeticInfo;
import com.vijay.TeleHealth.entity.SugarLevels;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.exception.HealthMetricException;
import com.vijay.TeleHealth.exception.MedicalHistoryException;
import com.vijay.TeleHealth.exception.ProfileDetailsException;
import com.vijay.TeleHealth.repository.DiabeticInfoRepository;
import com.vijay.TeleHealth.repository.PatientProfileRepository;
import com.vijay.TeleHealth.repository.SugarLevelsRepository;
import com.vijay.TeleHealth.repository.UserRepository;
import com.vijay.TeleHealth.service.FileStorageService;
import com.vijay.TeleHealth.service.HealthMetricServiceImpl;
import com.vijay.TeleHealth.service.MedicalHistoryServiceImpl;
import com.vijay.TeleHealth.service.PatientProfileServiceImpl;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/patient/profile")
public class PatientProfileController {
	private FileStorageService fileStorageService;

	@Autowired
	private PatientProfileServiceImpl profileServiceImpl;
	@Autowired
	private MedicalHistoryServiceImpl medicalHistoryService;
	@Autowired
	private HealthMetricServiceImpl healthMetricService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SugarLevelsRepository sugarRepository;
	@Autowired
	private PatientProfileRepository patientProfileRepository;
	@Autowired
	private DiabeticInfoRepository diabeticInfoRepository;

	public PatientProfileController(FileStorageService fileStorageService) {
		super();
		this.fileStorageService = fileStorageService;
	}

	@GetMapping("/healthMetric/{id}")
	public ResponseEntity<HealthMetricResponseDTO> getHealthMetric(@PathVariable Long id) throws HealthMetricException {
		return ResponseEntity.ok(healthMetricService.getHealthMetric(id));
	}

	@PostMapping("/send")
	public ResponseEntity<HealthMetricResponseDTO> createHealthMetric(@PathVariable Long id,
			@RequestBody HealthMetricRequestDTO dto) throws HealthMetricException {
		HealthMetricResponseDTO res = healthMetricService.createHealthMetric(id, dto.getHeight(), dto.getWeight(),
				dto.getBloodPressure(), dto.getHeartRate(), dto.getFastingLvl(), dto.getPostMealLvl(),
				dto.getRandomLvl());

		return new ResponseEntity<HealthMetricResponseDTO>(res, HttpStatus.CREATED);
	}

	@GetMapping("/healthMetric/check/{id}")
	public ResponseEntity<Boolean> checkMetricAvailability(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(sugarRepository.existsByUserId(id), HttpStatus.OK);
	}

	@GetMapping("/check/{id}")
	public ResponseEntity<Boolean> checkPersonalProfileAvailability(@PathVariable Long id) {
		return new ResponseEntity<Boolean>(patientProfileRepository.existsByUserId(id), HttpStatus.OK);
	}

	@GetMapping("/medical_history/{userId}")
	public ResponseEntity<?> getMedicalHistory(@PathVariable Long userId) {
		try {
			return ResponseEntity.ok(medicalHistoryService.getMedicalHistory(userId));
		} catch (MedicalHistoryException e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medical history not founf for user: " + userId);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error retrieving medical history: " + e.getMessage());
		}
	}

	@PostMapping(value = "/medical_history", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createMedicalHistory(@RequestParam("userId") Long userId,
			@RequestParam("allergies") String allergies, @RequestParam("previousConditions") String previousConditions,
			@RequestPart("medications") MultipartFile medicationsHistory) {
		try {
			String medicalHistoryPath = fileStorageService.storeFile(medicationsHistory, "medical_history");
			Optional<User> user = userRepository.findById(userId);
			if (user.isEmpty())
				return ResponseEntity.badRequest().body("User not found");
			if (user.get().getRole().equals(RoleType.ROLE_ADMIN) || user.get().getRole().equals(RoleType.ROLE_PROVIDER))
				throw new MedicalHistoryException(
						"Cannot create Medical history details for the role admin and provider");
			medicalHistoryService.createMedicalHistory(userId, allergies, previousConditions, medicalHistoryPath);
			return ResponseEntity.ok("Medical History created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while creating the medical history " + e.getMessage());
		}
	}

	@PostMapping("/{userId}")
	public ResponseEntity<PatientProfileResponseDTO> createOrUpdateProfile(@PathVariable Long userId,
			@RequestBody PatientProfileRequestDTO req) throws CustomException {

		return new ResponseEntity<PatientProfileResponseDTO>(profileServiceImpl.createOrUpdateProfile(userId, req),
				HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<PatientProfileResponseDTO> getProfile(@PathVariable Long userId)
			throws ProfileDetailsException {

		return new ResponseEntity<PatientProfileResponseDTO>(profileServiceImpl.getProfile(userId), HttpStatus.OK);
	}

	@PostMapping("/diabetic-info/update")
	@Transactional
	@Modifying
	public ResponseEntity<?> updateOrInsertSugar(@RequestBody DiabeticInfo dto, @RequestParam Long patientId) {
		Optional<User> patient = userRepository.findById(patientId);

		if (patient == null)
			return ResponseEntity.badRequest().body("Patient not found");

		DiabeticInfo i = new DiabeticInfo();
		i.setFastingLvl(dto.getFastingLvl());
		i.setPostMealLvl(dto.getPostMealLvl());
		i.setRandomLvl(dto.getRandomLvl());
		i.setUpdatedOn(LocalDate.now());
		i.setPatient(patient.get());

		Optional<SugarLevels> s = sugarRepository.findByUser(patient.get());
		s.get().setFastingLvl(dto.getFastingLvl());
		s.get().setPostMealLvl(dto.getPostMealLvl());
		s.get().setRandomLvl(dto.getRandomLvl());
		sugarRepository.save(s.get());
		diabeticInfoRepository.save(i);

		return ResponseEntity.ok("Saved Successfully");
	}
}
