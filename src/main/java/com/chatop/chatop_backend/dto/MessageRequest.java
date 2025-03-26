package com.chatop.chatop_backend.dto;

import com.chatop.chatop_backend.entity.Message;

import lombok.Builder;

@Builder
public record MessageRequest(Long rental_id, Long user_id, String message) {
	
	public static MessageRequest fromEntity(Message message) {
		return  MessageRequest.builder()
                .rental_id(message.getRental().getId())
                .user_id(message.getUser().getId())
                .message(message.getMessage())
                .build();
	}
}
