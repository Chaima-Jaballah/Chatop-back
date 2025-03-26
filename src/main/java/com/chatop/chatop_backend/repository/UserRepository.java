package com.chatop.chatop_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.chatop.chatop_backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
