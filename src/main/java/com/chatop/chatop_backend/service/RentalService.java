package com.chatop.chatop_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.chatop_backend.entity.Rental;

public interface RentalService {

	List<Rental> getAllRentals();

	Optional<Rental> findById(Long id);

	ResponseEntity<?> save(String name, Double surface, Double price, String description, Long userId,
			MultipartFile file);

	ResponseEntity<?> update(Long rentalId, String name, Double surface, Double price, String description);

	List<Rental> getByUserId(Long userId);
}
