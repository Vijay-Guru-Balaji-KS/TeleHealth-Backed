package com.vijay.TeleHealth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijay.TeleHealth.entity.Appointment;
import com.vijay.TeleHealth.enums.AppointmentStatus;
import com.vijay.TeleHealth.exception.AppointmentException;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
	
	List<Appointment> findByPatientId(Long patientId);
	
	List<Appointment> findByProviderId(Long providerId);
	
	Optional<Appointment> findById(Long id);
	
	List<Appointment> findByProvider_IdAndStatus(Long providerId, AppointmentStatus status);
	
	@Query("select a from Appointment a where a.slot_id = :slotId")
	Optional<Appointment> findBySlotId(@Param("slotId") Long slotId) throws AppointmentException;
}
