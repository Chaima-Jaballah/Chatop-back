package com.chatop.chatop_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.chatop.chatop_backend.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByOwnerId(Long userId); 
}
