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
@Table(name="users")
public class User {
	@Id @GeneratedValue
    private Long id;
	private String email;
    private String name;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    
}

