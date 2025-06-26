package com.vijay.TeleHealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.MedicalHistoryResponseDTO;
import com.vijay.TeleHealth.entity.MedicalHistory;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.HealthMetricException;
import com.vijay.TeleHealth.exception.MedicalHistoryException;
import com.vijay.TeleHealth.repository.MedicalHistoryRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicalHistoryServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;

	public String createMedicalHistory(Long patientId, String allergies, String previousConditions,
			String MedicationsPath) throws MedicalHistoryException {
		User user = userRepository.findById(patientId)
				.orElseThrow(() -> new MedicalHistoryException("Patient is not available"));

		if (!user.getRole().equals(RoleType.ROLE_PATIENT))
			throw new MedicalHistoryException("Medical History details can be given only to the patient role");

		MedicalHistory h = new MedicalHistory(allergies, previousConditions, MedicationsPath, user);
		medicalHistoryRepository.save(h);
		return "Medical History successfully created";
	}

	public MedicalHistoryResponseDTO getMedicalHistory(Long userId) throws MedicalHistoryException {
		MedicalHistory history = medicalHistoryRepository.getHistoryBasedOnUser(userId)
				.orElseThrow(() -> new MedicalHistoryException("No Medical History about the user"));
		MedicalHistoryResponseDTO dto = new MedicalHistoryResponseDTO(history.getAllergies(),
				history.getPreviousConditions(), history.getMedications());
		return dto;
	}
}
