package com.vijay.TeleHealth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatMessage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long senderId;

	private Long receiverId;

	private Long appointmentId;

	private String content;

	private LocalDateTime timestamp;

	public ChatMessage() {
	}

	public ChatMessage(Long id, Long senderId, Long receiverId, Long appointmentId, String content,
			LocalDateTime timestamp) {
		super();
		this.id = id;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.appointmentId = appointmentId;
		this.content = content;
		this.timestamp = timestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ChatMessage [id=" + id + ", senderId=" + senderId + ", receiverId=" + receiverId + ", appointmentId="
				+ appointmentId + ", content=" + content + ", timestamp=" + timestamp + "]";
	}

}
