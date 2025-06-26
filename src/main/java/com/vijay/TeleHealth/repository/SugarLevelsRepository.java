package com.vijay.TeleHealth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vijay.TeleHealth.entity.SugarLevels;
import com.vijay.TeleHealth.entity.User;

public interface SugarLevelsRepository extends JpaRepository<SugarLevels, Long>{
	Optional<SugarLevels> findByUser(User user);
	
	Boolean existsByUserId(Long userId);
	
	@Query("select s from SugarLevels s where s.user.id = :id")
	Optional<SugarLevels> findUserById(@Param("id") Long id);
}
