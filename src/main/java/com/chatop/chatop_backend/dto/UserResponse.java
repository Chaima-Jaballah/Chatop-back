package com.chatop.chatop_backend.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.chatop.chatop_backend.entity.User;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email, String created_at, String updated_at) {
	
	public static UserResponse fromEntity(User user) {
		LocalDateTime localCreatedAtDateTime = user.getCreatedAt().toLocalDateTime();
		LocalDateTime localUpdatedAtDateTime = user.getUpdatedAt().toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		String createdAt = localCreatedAtDateTime.format(formatter);
		String updatedAt = localUpdatedAtDateTime.format(formatter);
		return UserResponse.builder().id(user.getId())
				.name(user.getName()).email(user.getEmail()).created_at(createdAt).updated_at(updatedAt).build();
		
	}
}
