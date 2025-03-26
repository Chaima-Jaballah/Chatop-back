package com.chatop.chatop_backend.entity;
import lombok.*;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="messages")
public class Message {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rental_id", referencedColumnName = "id", nullable = false)
    private Rental rental;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
