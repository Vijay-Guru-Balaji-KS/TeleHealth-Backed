package com.vijay.TeleHealth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vijay.TeleHealth.dto.ProviderProfileResponseDTO;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.ProviderAvailabilityException;
import com.vijay.TeleHealth.exception.ProviderProfileException;
import com.vijay.TeleHealth.repository.ProviderProfileRepository;
import com.vijay.TeleHealth.repository.UserRepository;
import com.vijay.TeleHealth.service.FileStorageService;
import com.vijay.TeleHealth.service.ProviderProfileServiceImpl;

@RestController
@RequestMapping("/api/provider/profile")
public class ProviderProfileController {

	private FileStorageService fileStorageService;
	@Autowired
	private ProviderProfileServiceImpl providerProfileService;
	@Autowired
	private ProviderProfileRepository providerProfileRepository;

	@Autowired
	private UserRepository userRepository;

	public ProviderProfileController(FileStorageService fileStorageService) {
		super();
		this.fileStorageService = fileStorageService;
	}

	@GetMapping("/check/{userId}")
	public ResponseEntity<Boolean> checkProviderProfile(@PathVariable Long userId) {
		boolean hasProfile = providerProfileRepository.existsByUserId(userId);
		return ResponseEntity.ok(hasProfile);
	}

	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createProviderProfile(@RequestParam("userId") Long userId,
			@RequestParam("specialization") String specialization, @RequestParam("bio") String bio,
			@RequestPart("certificate") MultipartFile certificateFile,
			@RequestPart("profilePhoto") MultipartFile profilePhotoFile) {
		try {
			String certificatePath = fileStorageService.storeFile(certificateFile, "certificates");
			String profilePhotoPath = fileStorageService.storeFile(profilePhotoFile, "profile_photos");
			Optional<User> user = userRepository.findById(userId);
			if (user.isEmpty())
				return ResponseEntity.badRequest().body("User not found");
			if (user.get().getRole().equals(RoleType.ROLE_ADMIN) || user.get().getRole().equals(RoleType.ROLE_PATIENT))
				throw new ProviderProfileException(
						"Cannot create provider details profile for the role admin and patient");
			providerProfileService.createProfile(user.get(), specialization, bio, certificatePath, profilePhotoPath);
			return ResponseEntity.ok("Provider Profile created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while creating the profile " + e.getMessage());
		}
	}

	@GetMapping("/{providerId}")
	public ResponseEntity<ProviderProfileResponseDTO> getProviderProfile(@PathVariable Long providerId)
			throws ProviderAvailabilityException {
		ProviderProfileResponseDTO profileDTO = providerProfileService.getProfileByUserId(providerId);
		return new ResponseEntity<ProviderProfileResponseDTO>(profileDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProviderProfile(@RequestParam("userId") Long userId,
			@RequestParam("specialization") String specialization, @RequestParam("bio") String bio,
			@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("phoneNumber") String phoneNumber, @RequestPart("certificate") MultipartFile certificateFile,
			@RequestPart("profilePhoto") MultipartFile profilePhotoFile) {
		try {

			Optional<User> Opuser = userRepository.findById(userId);
			if (Opuser.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");

			User user = Opuser.get();
			user.setEmail(email);
			user.setName(name);
			user.setPhoneNumber(phoneNumber);
			userRepository.save(user);

			var providerProfile = providerProfileRepository.findByUserId(userId)
					.orElseThrow(() -> new ProviderProfileException("Provider profile not found"));
			providerProfile.setSpecialization(specialization);
			providerProfile.setBio(bio);

			if (certificateFile != null && !certificateFile.isEmpty()) {
				String newCertificatePath = fileStorageService.storeFile(certificateFile, "certificates");
				providerProfile.setCertificationDetails(newCertificatePath);
			}

			if (profilePhotoFile != null && !profilePhotoFile.isEmpty()) {
				String newProfilePhotoPath = fileStorageService.storeFile(profilePhotoFile, "profile_photos");
				providerProfile.setProfilePhotoPath(newProfilePhotoPath);
			}

			providerProfileRepository.save(providerProfile);

			return ResponseEntity.ok("Provider updated Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occured while updating the provider profile " + e.getMessage());
		}
	}
}
