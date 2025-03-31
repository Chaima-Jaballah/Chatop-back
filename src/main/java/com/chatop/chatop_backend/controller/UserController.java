package com.chatop.chatop_backend.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatop_backend.dto.UserResponse;
import com.chatop.chatop_backend.entity.User;
import com.chatop.chatop_backend.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserServiceImpl userService;

	@GetMapping("/{idUser}")
	public ResponseEntity<?> findUserById(@PathVariable Long idUser) {
		Optional<User> optionalUser = this.userService.findById(idUser);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			return ResponseEntity.status(HttpStatus.OK).body(UserResponse.fromEntity(user));
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}

}
