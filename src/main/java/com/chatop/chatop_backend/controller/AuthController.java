package com.chatop.chatop_backend.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.chatop_backend.dto.AuthRequest;
import com.chatop.chatop_backend.dto.AuthResponse;
import com.chatop.chatop_backend.dto.RegisterRequest;
import com.chatop.chatop_backend.dto.UserResponse;
import com.chatop.chatop_backend.entity.User;
import com.chatop.chatop_backend.security.CustomUserDetailsService;
import com.chatop.chatop_backend.security.JwtUtil;
import com.chatop.chatop_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
		if (registerRequest.name() == null || registerRequest.email() == null || registerRequest.password() == null) {
			return ResponseEntity.badRequest().body(Map.of("message", "Missing required fields"));
		}

		if (userService.findByEmail(registerRequest.email()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Email already in use"));
		}
		User user = new User();
		user.setPassword(passwordEncoder.encode(registerRequest.password())); // (à remplacer par vrai hash)
		Timestamp now = new Timestamp(System.currentTimeMillis());
		user.setCreatedAt(now);
		user.setUpdatedAt(now);
		user.setEmail(registerRequest.email());
		user.setName(registerRequest.name());
		userService.save(user);

		String token = JwtUtil.generateToken(user.getEmail());
		return ResponseEntity.ok(AuthResponse.builder().token(token).build());
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest loginRequest) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), // updated
					loginRequest.password()));

			UserDetails user = userDetailsService.loadUserByUsername(loginRequest.email());
			String token = JwtUtil.generateToken(user.getUsername()); // username = email in this case

			return ResponseEntity.ok(AuthResponse.builder().token(token).build());
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}

	@GetMapping("/me")
	public ResponseEntity<?> getMe(Principal principal) {
		Optional<User> optionalCurrentUser = this.userService.findByEmail(principal.getName());
		if (optionalCurrentUser.isPresent()) {
			User user = optionalCurrentUser.get();
			return ResponseEntity.status(HttpStatus.OK).body(UserResponse.fromEntity(user));
		}

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not Authenticated");
	}
}
