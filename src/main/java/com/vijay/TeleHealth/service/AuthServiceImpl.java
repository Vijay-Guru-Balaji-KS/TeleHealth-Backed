package com.vijay.TeleHealth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.AuthRequestDTO;
import com.vijay.TeleHealth.dto.AuthResponseDTO;
import com.vijay.TeleHealth.dto.RegisterRequestDTO;
import com.vijay.TeleHealth.dto.UserDTO;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public AuthResponseDTO register(RegisterRequestDTO request) throws CustomException {
		if (userRepository.exsistsByEmail(request.getEmail()))
			throw new CustomException("Email already registered");
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(request.getRole() != null ? request.getRole() : RoleType.ROLE_PATIENT);
		user.setEnabled(true);
		userRepository.save(user);

		return new AuthResponseDTO(user.getId(), request.getEmail(), "User registered successfully", user.getRole());
	}

	public AuthResponseDTO login(AuthRequestDTO request) throws CustomException {
		User user;
		if (request.getIdentifier().contains("@"))
			user = userRepository.findByEmail(request.getIdentifier())
					.orElseThrow(() -> new CustomException("AuthServieImpl.InvalidEmail"));
		else {
			user = userRepository.findByPhoneNumber(request.getIdentifier())
					.orElseThrow(() -> new CustomException("AuthServieImpl.InvalidPhoneNumber"));
		}

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
			throw new CustomException("AuthServiceImpl.InvalidPassword");
		return new AuthResponseDTO(user.getId(), user.getEmail(), "Login successful", user.getRole());
	}

	public List<UserDTO> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		return usersList.stream().map(u -> {
			return new UserDTO(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.getPhoneNumber(), u.getRole());
		}).collect(Collectors.toList());
	}
}
