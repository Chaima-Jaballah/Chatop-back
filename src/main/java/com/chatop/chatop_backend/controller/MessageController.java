package com.chatop.chatop_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatop_backend.dto.MessageRequest;
import com.chatop.chatop_backend.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@PostMapping
	public ResponseEntity<?> sendMessage(@RequestBody MessageRequest message) {
		return messageService.save(message);
	}

}
