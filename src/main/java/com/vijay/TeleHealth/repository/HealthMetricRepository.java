package com.vijay.TeleHealth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.TeleHealth.entity.HealthMetrics;

public interface HealthMetricRepository extends JpaRepository<HealthMetrics, Long>{

}
