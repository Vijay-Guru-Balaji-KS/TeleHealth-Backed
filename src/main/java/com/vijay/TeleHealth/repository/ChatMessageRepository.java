package com.vijay.TeleHealth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vijay.TeleHealth.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
	List<ChatMessage> findByAppointmentIdOrderByTimestampAsc(Long appointmentId);
}
