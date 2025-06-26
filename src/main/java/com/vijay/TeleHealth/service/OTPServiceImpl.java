package com.vijay.TeleHealth.service;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OTPServiceImpl {
	private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

	public String generateOtp(String email) {
		String normalizedEmail = email.trim().toLowerCase();
		String otp = String.format("%06d", new Random().nextInt(999999));
		otpStorage.put(normalizedEmail, otp);
		return otp;
	}

	public boolean validateOtp(String email, String otp) {
		String normalizedEmail = email.trim().toLowerCase();
		String validOtp = otpStorage.get(normalizedEmail);
		return validOtp != null && validOtp.equals(otp);
	}

	public void clearOtp(String email) {
		String normalizedEmail = email.trim().toLowerCase();
		otpStorage.remove(normalizedEmail);
	}
}
