package com.chatop.chatop_backend.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.chatop_backend.dto.RentalListResponse;
import com.chatop.chatop_backend.dto.RentalResponse;
import com.chatop.chatop_backend.entity.Rental;
import com.chatop.chatop_backend.service.RentalServiceImpl;
import com.chatop.chatop_backend.service.UserServiceImpl;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	@Autowired
	private RentalServiceImpl rentalService;

	@Autowired
	UserServiceImpl userService;

	@GetMapping
	public ResponseEntity<RentalListResponse> getAll() {
		return ResponseEntity.ok(RentalListResponse.builder().rentals(
				rentalService.getAllRentals().stream().map(rental -> RentalResponse.fromEntity(rental)).toList())
				.build());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RentalResponse> getById(@PathVariable Long id) {
		Optional<Rental> optionalRental = rentalService.findById(id);
		if (optionalRental.isPresent()) {
			Rental rental = optionalRental.get();
			return ResponseEntity.ok(RentalResponse.fromEntity(rental));
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<?> createRental(@RequestParam String name, @RequestParam Double surface,
			@RequestParam Double price, @RequestParam String description, @RequestParam("picture") MultipartFile file,
			Principal principal) {
		Long userId = this.userService.findByEmail(principal.getName()).orElse(null).getId();
		return this.rentalService.save(name, surface, price, description, userId, file);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateRental(@PathVariable Long id, @RequestParam String name,
			@RequestParam Double surface, @RequestParam Double price, @RequestParam String description) {
		return this.rentalService.update(id, name, surface, price, description);
	}

}