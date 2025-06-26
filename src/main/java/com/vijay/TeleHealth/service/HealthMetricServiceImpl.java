package com.vijay.TeleHealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vijay.TeleHealth.dto.HealthMetricResponseDTO;
import com.vijay.TeleHealth.entity.HealthMetrics;
import com.vijay.TeleHealth.entity.SugarLevels;
import com.vijay.TeleHealth.entity.User;
import com.vijay.TeleHealth.enums.RoleType;
import com.vijay.TeleHealth.exception.CustomException;
import com.vijay.TeleHealth.exception.HealthMetricException;
import com.vijay.TeleHealth.repository.HealthMetricRepository;
import com.vijay.TeleHealth.repository.SugarLevelsRepository;
import com.vijay.TeleHealth.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HealthMetricServiceImpl {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HealthMetricRepository healthMetricsRepository;

	@Autowired
	private SugarLevelsRepository sugarLevelsRepository;

	public HealthMetricResponseDTO createHealthMetric(Long userId, Float height, Float weight, String bloodPressure,
			Integer heartRate, Integer fastingLvl, Integer postMealLvl, Integer randomLvl)
			throws HealthMetricException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new HealthMetricException("Patient is not available"));

		if (user.getRole().equals(RoleType.ROLE_ADMIN) || user.getRole().equals(RoleType.ROLE_PROVIDER))
			throw new HealthMetricException("Only patients role can have health metric section");

		SugarLevels sugar = sugarLevelsRepository.findByUser(user).orElse(null);

		if (sugar == null) {
			sugar = new SugarLevels();
			sugar.setUser(user);
		}

		sugar.setFastingLvl(fastingLvl);
		sugar.setPostMealLvl(postMealLvl);
		sugar.setRandomLvl(randomLvl);
		sugarLevelsRepository.save(sugar);

		HealthMetrics hmetrics = new HealthMetrics(height, weight, bloodPressure, heartRate, sugar);

		healthMetricsRepository.save(hmetrics);

		return new HealthMetricResponseDTO(hmetrics.getId(), height, weight, bloodPressure, fastingLvl, postMealLvl,
				randomLvl, heartRate);
	}

	public HealthMetricResponseDTO getHealthMetric(Long user_id) throws HealthMetricException {
		SugarLevels sugar = sugarLevelsRepository.findUserById(user_id)
				.orElseThrow(() -> new HealthMetricException("No sugar details for the req user_id"));
		HealthMetrics metrics = healthMetricsRepository.findById(sugar.getId())
				.orElseThrow(() -> new HealthMetricException("No Health Metrics detail avlb for the sugar_id"));
		HealthMetricResponseDTO dto = new HealthMetricResponseDTO(user_id, metrics.getHeight(), metrics.getWeight(),
				metrics.getBloodPressure(), sugar.getFastingLvl(), sugar.getPostMealLvl(), sugar.getRandomLvl(),
				metrics.getHeartRate());
		return dto;
	}
}
