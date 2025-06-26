package com.vijay.TeleHealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.PatientProfileRequestDTO;
import com.vijay.TeleHealth.dto.PatientProfileResponseDTO;
import com.vijay.TeleHealth.entity.PatientProfile;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.exception.ProfileDetailsException;
import com.vijay.TeleHealth.repository.PatientProfileRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientProfileServiceImpl {

	@Autowired
	PatientProfileRepository patientProfileRepository;

	@Autowired
	private UserRepository userRepository;

	public PatientProfileResponseDTO createOrUpdateProfile(Long userId, PatientProfileRequestDTO request)
			throws CustomException {
		User user = userRepository.findById(userId).orElseThrow(() -> new CustomException("user not found"));

		if (!user.getRole().equals(RoleType.ROLE_PATIENT))
			throw new CustomException("Patient Personal profile creation is possible for patient role only");

		PatientProfile p = patientProfileRepository.findByUserId(userId).orElse(new PatientProfile());
		p.setUser(user);
		p.setGender(request.getGender());
		p.setDateOfBirth(request.getDateOfBirth());
		p.setAddress(request.getAddress());
		p.setInsuranceNumber(request.getInsuranceNumber());
		p.setInsuranceProvider(request.getInsuranceProvider());
		p.setPhoneNumber(user.getPhoneNumber());

		PatientProfile c = patientProfileRepository.save(p);

		PatientProfileResponseDTO resDTO = new PatientProfileResponseDTO(c.getId(), user.getName(), user.getEmail(),
				c.getGender(), c.getDateOfBirth(), c.getAddress(), c.getInsuranceProvider(), c.getInsuranceNumber(),
				user.getPhoneNumber());
		return resDTO;
	}

	public PatientProfileResponseDTO getProfile(Long userId) throws ProfileDetailsException {
		PatientProfile p = patientProfileRepository.findByUserId(userId)
				.orElseThrow(() -> new ProfileDetailsException("Profile not found"));

		User user = p.getUser();

		PatientProfileResponseDTO res = new PatientProfileResponseDTO(p.getId(), user.getName(), user.getEmail(),
				p.getGender(), p.getDateOfBirth(), p.getAddress(), p.getInsuranceProvider(), p.getInsuranceNumber(),
				user.getPhoneNumber());
		return res;
	}
}
