package com.vijay.TeleHealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.TeleHealth.entity.ProviderProfile;

public interface ProviderProfileRepository extends JpaRepository<ProviderProfile, Long> {
	Optional<ProviderProfile> findByUserId(Long userId);

	Boolean existsByUserId(Long userId);
}
