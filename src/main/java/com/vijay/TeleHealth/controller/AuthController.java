package com.vijay.TeleHealth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.TeleHealth.dto.AuthRequestDTO;
import com.vijay.TeleHealth.dto.AuthResponseDTO;
import com.vijay.TeleHealth.dto.RegisterRequestDTO;
import com.vijay.TeleHealth.dto.UserDTO;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.service.AuthServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

	@Autowired
	private AuthServiceImpl authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterRequestDTO req) throws CustomException {
		AuthResponseDTO dto = authService.register(req);
		return new ResponseEntity<AuthResponseDTO>(dto, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO req) throws CustomException {
		AuthResponseDTO dto = authService.login(req);
		return new ResponseEntity<AuthResponseDTO>(dto, HttpStatus.CREATED);
	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<List<UserDTO>>(authService.getAllUsers(), HttpStatus.OK);
	}
}
