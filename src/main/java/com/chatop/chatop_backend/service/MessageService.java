package com.chatop.chatop_backend.service;

import org.springframework.http.ResponseEntity;

import com.chatop.chatop_backend.dto.MessageRequest;

public interface MessageService {
	ResponseEntity<?> save(MessageRequest dto);
}
