package com.vijay.TeleHealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.ProviderProfessionalDetailsDTO;
import com.vijay.TeleHealth.dto.ProviderProfileResponseDTO;
import com.vijay.TeleHealth.entity.ProviderProfile;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.repository.ProviderProfileRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProviderProfileServiceImpl {

	@Autowired
	private ProviderProfileRepository providerProfileRepository;
	@Autowired
	private UserRepository userRepository;

	public String createProfile(User user, String specialization, String bio, String certificatePath,
			String profilePhotoPath) {
		ProviderProfile p = new ProviderProfile(user, specialization, certificatePath, profilePhotoPath, bio);
		providerProfileRepository.save(p);
		return "Profile creation Successful";
	}

	public ProviderProfileResponseDTO getProfileByUserId(Long providerId) throws ProviderAvailabilityException {
		ProviderProfile profile = providerProfileRepository.findByUserId(providerId)
				.orElseThrow(() -> new RuntimeException("Provider profile not found"));

		User provider = (userRepository.findById(providerId))
				.orElseThrow(() -> new ProviderAvailabilityException("Provider is not in users table"));

		ProviderProfessionalDetailsDTO pdto = new ProviderProfessionalDetailsDTO(provider.getName(),
				provider.getEmail(), provider.getPhoneNumber());

		ProviderProfileResponseDTO dto = new ProviderProfileResponseDTO(profile.getId(), profile.getBio(),
				profile.getCertificationDetails(), profile.getProfilePhotoPath(), profile.getSpecialization(), pdto);
		return dto;
	}
}
