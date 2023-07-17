package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.config.security.jwt.JwtGenerator;
import com.example.airtimevtuapi.data.dtos.request.LoginRequest;
import com.example.airtimevtuapi.data.dtos.response.TokenResponse;
import com.example.airtimevtuapi.exceptions.InvalidLoginDetailsException;
import com.example.airtimevtuapi.services.Interfaces.AuthenticationService;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static com.example.airtimevtuapi.common.Message.LOGIN_FAILED;


@Service
@RequiredArgsConstructor
public class AuthenticationImpl implements AuthenticationService {
    private final AuthenticationManager manager;
    private final JwtGenerator generator;
    @Override
    public TokenResponse authenticate(@NotNull LoginRequest request) {
        try {
            // Authenticates the user using the provided email and password
            Authentication authentication = manager.authenticate (
                    new UsernamePasswordAuthenticationToken (request.getEmail (),
                            request.getPassword ()
                    )
            );
            // Generates an access token with a 60-second expiration
            String accessToken = generator.generateToken (authentication, Long.valueOf (60000));
            // Generates a refresh token with a 7-day expiration
            String refreshToken = generator.generateToken (authentication, Long.valueOf(604800000));

            // Builds and returns a TokenResponse object containing the access and refresh tokens
            return TokenResponse.builder ()
                    .accessToken ("Bearer "+accessToken)
                    .refreshToken ("Bearer "+refreshToken)
                    .build ();
        }catch (Exception e){
            // Throws an InvalidLoginDetailsException if authentication fails
            throw new InvalidLoginDetailsException(LOGIN_FAILED);
        }
    }


}

