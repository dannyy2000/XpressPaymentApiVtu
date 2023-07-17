package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.models.AuthToken;

import java.util.Optional;

public interface AuthTokenService {

    void saveAuthToken(AuthToken token);

    Optional<AuthToken> checkToken(String token);

    void setConfirmedAt(String token);
}
