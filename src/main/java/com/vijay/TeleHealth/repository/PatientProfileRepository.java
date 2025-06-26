package com.vijay.TeleHealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.TeleHealth.entity.PatientProfile;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long>{
	Optional<PatientProfile> findByUserId(Long userId);
	Boolean existsByUserId(Long userId);
}
