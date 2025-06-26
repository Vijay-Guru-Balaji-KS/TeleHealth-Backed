package com.vijay.TeleHealth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.TeleHealth.service.EmailService;
import com.vijay.TeleHealth.service.OTPServiceImpl;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

	@Autowired
	private OTPServiceImpl otpService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<Map<String, String>> sendOtp(@RequestParam String email) {
		String otp = otpService.generateOtp(email);
		emailService.sendOtp(email, otp);

		Map<String, String> res = new HashMap<>();
		res.put("message", "OTP sent to email");

		return ResponseEntity.ok(res);
	}

	@PostMapping("/verify")
	public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
		boolean valid = otpService.validateOtp(email, otp);
		if (valid) {
			otpService.clearOtp(email);
			return ResponseEntity.ok("OTP Verified");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
		}
	}
}
