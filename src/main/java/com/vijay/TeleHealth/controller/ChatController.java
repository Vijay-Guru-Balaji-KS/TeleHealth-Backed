package com.vijay.TeleHealth.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.TeleHealth.entity.ChatMessage;
import com.vijay.TeleHealth.repository.ChatMessageRepository;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private ChatMessageRepository chatMessageRepository;

	@PostMapping
	public ResponseEntity<Void> sendMessage(@RequestBody ChatMessage message) {
		message.setTimestamp(LocalDateTime.now());
		chatMessageRepository.save(message);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{appointmentId}")
	public ResponseEntity<List<ChatMessage>> getMessage(@PathVariable Long appointmentId) {
		List<ChatMessage> msg = chatMessageRepository.findByAppointmentIdOrderByTimestampAsc(appointmentId);
		return ResponseEntity.ok(msg);
	}
}
