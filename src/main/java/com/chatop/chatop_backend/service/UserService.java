package com.chatop.chatop_backend.service;

import java.util.Optional;

import com.chatop.chatop_backend.entity.User;

public interface UserService {

	Optional<User> findByEmail(String email);

	User save(User user);

	Optional<User> findById(Long id);

}
