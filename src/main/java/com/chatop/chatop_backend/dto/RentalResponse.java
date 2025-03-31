package com.chatop.chatop_backend.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.chatop.chatop_backend.entity.Rental;

import lombok.Builder;

@Builder
public record RentalResponse(Long id, String name, Double surface, Double price, List<String> picture,
		String description, Long owner_id, String created_at, String updated_at) {

	public static RentalResponse fromEntity(Rental rental) {
		LocalDateTime localCreatedAtDateTime = rental.getCreatedAt().toLocalDateTime();
		LocalDateTime localUpdatedAtDateTime = rental.getUpdatedAt().toLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); // Format the date
		String createdAt = localCreatedAtDateTime.format(formatter);
		String updatedAt = localUpdatedAtDateTime.format(formatter);
		String pictureUrl = "http://localhost:4200/assets" + rental.getPicture();
		
		return RentalResponse.builder().id(rental.getId()).name(rental.getName()).owner_id(rental.getOwner().getId())
				.description(rental.getDescription()).picture(List.of(pictureUrl)).price(rental.getPrice())
				.surface(rental.getSurface()).created_at(createdAt).updated_at(updatedAt).build();
	}
}
