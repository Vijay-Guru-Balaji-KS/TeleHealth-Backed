package com.vijay.TeleHealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijay.TeleHealth.entity.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long>{
	Optional<MedicalHistory> findByUserId(Long userId);
	
	@Query("select m from MedicalHistory m where m.user.id = :id")
	Optional<MedicalHistory> getHistoryBasedOnUser(@Param("id") Long id);
}
