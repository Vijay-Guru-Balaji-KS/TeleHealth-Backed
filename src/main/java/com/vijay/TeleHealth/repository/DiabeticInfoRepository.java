package com.vijay.TeleHealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.TeleHealth.entity.DiabeticInfo;

public interface DiabeticInfoRepository extends JpaRepository<DiabeticInfo, Long>{
	List<DiabeticInfo> findByPatientIdOrderByUpdatedOnAsc(Long patientId);
}
