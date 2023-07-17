package com.example.airtimevtuapi.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthToken {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "token_id"
    )
    private Long Id;
    private String token;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime expiresAt = createdAt.plusMinutes (20);
    private String email;
    private LocalDateTime confirmedAt;

    public AuthToken(String token, LocalDateTime created, LocalDateTime expiryDate, String appUser) {
        this.token = token;
        this.createdAt = created;
        this.expiresAt = expiryDate;
        this.email = appUser;
    }
}
