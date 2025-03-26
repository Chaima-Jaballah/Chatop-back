package com.chatop.chatop_backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.chatop.chatop_backend.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByRentalId(Long rentalId); 
    List<Message> findByUserId(Long userId);
}
