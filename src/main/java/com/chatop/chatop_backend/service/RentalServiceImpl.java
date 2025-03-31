package com.chatop.chatop_backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.chatop_backend.dto.RentalCrudResponse;
import com.chatop.chatop_backend.entity.Rental;
import com.chatop.chatop_backend.entity.User;
import com.chatop.chatop_backend.repository.RentalRepository;


@Service
public class RentalServiceImpl implements RentalService{
	@Autowired
	private RentalRepository rentalRepository;
	
	@Value("${file.upload-dir}")
    private String uploadDir;

	@Override
	public List<Rental> getAllRentals() {
		return rentalRepository.findAll();
	}

	@Override
	public Optional<Rental> findById(Long id) {
		return rentalRepository.findById(id);
	}

	@Override
	public ResponseEntity<?> save(String name, Double surface, Double price, String description, Long userId,
			MultipartFile file) {
		String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
		Path path = Paths.get(uploadDir + filename);
		try {
			Files.createDirectories(path.getParent());
			Files.copy(file.getInputStream(), path);
			Rental rental = new Rental();
			rental.setName(name);
			rental.setSurface(surface);
			rental.setPrice(price);
			rental.setDescription(description);
			rental.setPicture("/uploads/" + filename);

			Timestamp now = new Timestamp(System.currentTimeMillis());
			rental.setCreatedAt(now);
			rental.setUpdatedAt(now);

			User user = new User();
			user.setId(userId);

			rental.setOwner(user);
			
	        rentalRepository.save(rental);
	        return ResponseEntity.status(HttpStatus.OK).body(RentalCrudResponse.builder().message("Rental created !").build());


		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error while uploading image" + e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error while inserting rental" + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> update(Long rentalId, String name, Double surface, Double price, String description) {

		try {
			Rental rentalToUpdate = this.rentalRepository.findById(rentalId).orElseThrow(() -> {
				return new RuntimeException("Rental not found !");
			});

			Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
			rentalToUpdate.setName(name);
			rentalToUpdate.setSurface(surface);
			rentalToUpdate.setPrice(price);
			rentalToUpdate.setDescription(description);
			rentalToUpdate.setUpdatedAt(updatedAt);

			this.rentalRepository.save(rentalToUpdate);
			return ResponseEntity.status(HttpStatus.OK)
					.body(RentalCrudResponse.builder().message("Rental updated !").build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Error while updating rental" + e.getMessage());
		}

	}

	@Override
	public List<Rental> getByUserId(Long userId) {
		return rentalRepository.findByOwnerId(userId);
	}

}
