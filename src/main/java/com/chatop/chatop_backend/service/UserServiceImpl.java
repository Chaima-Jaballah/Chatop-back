package com.chatop.chatop_backend.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import com.chatop.chatop_backend.entity.User;
import com.chatop.chatop_backend.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
    private UserRepository userRepository;

	@Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

	@Override
    public User save(User user) {
        return userRepository.save(user);
    }

	@Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
