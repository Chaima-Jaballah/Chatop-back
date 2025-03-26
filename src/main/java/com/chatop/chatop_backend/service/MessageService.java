package com.chatop.chatop_backend.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatop.chatop_backend.dto.MessageRequest;
import com.chatop.chatop_backend.dto.MessageResponse;
import com.chatop.chatop_backend.entity.Message;
import com.chatop.chatop_backend.entity.Rental;
import com.chatop.chatop_backend.entity.User;
import com.chatop.chatop_backend.repository.MessageRepository;
import com.chatop.chatop_backend.repository.RentalRepository;
import com.chatop.chatop_backend.repository.UserRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private RentalRepository rentalRepository;
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> save(MessageRequest dto) {

		try {
			User user = userRepository.findById(dto.user_id())
					.orElseThrow(() -> new RuntimeException("User not found"));

			Rental rental = rentalRepository.findById(dto.rental_id())
					.orElseThrow(() -> new RuntimeException("Rental not found"));

			Message message = Message.builder().message(dto.message())
					.createdAt(new Timestamp(System.currentTimeMillis())).user(user).rental(rental).build();
			messageRepository.save(message);
			return ResponseEntity.status(HttpStatus.OK)
					.body(MessageResponse.builder().message("Message send with success").build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error while inserting message :" + e.getMessage());
		}

	}
}
