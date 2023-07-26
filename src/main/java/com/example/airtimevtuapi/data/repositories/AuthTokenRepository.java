package com.example.airtimevtuapi.data.repositories;

import com.example.airtimevtuapi.data.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken,Long> {

    Optional <AuthToken> findAuthByToken(String token);


    Optional<AuthToken> findByToken(String token);
}
