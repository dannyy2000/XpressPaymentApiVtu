package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.dtos.request.LoginRequest;
import com.example.airtimevtuapi.data.dtos.response.TokenResponse;
import jakarta.validation.constraints.NotNull;

public interface AuthenticationService {

    TokenResponse authenticate (@NotNull LoginRequest request);
}
