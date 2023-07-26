package com.example.airtimevtuapi.controllers;

import com.example.airtimevtuapi.common.ApiResponse;
import com.example.airtimevtuapi.data.dtos.request.LoginRequest;
import com.example.airtimevtuapi.data.dtos.request.SignUpRequest;
import com.example.airtimevtuapi.data.dtos.response.TokenResponse;
import com.example.airtimevtuapi.services.Interfaces.AuthenticationService;
import com.example.airtimevtuapi.services.Interfaces.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SignUpService signUpService;
    private final AuthenticationService authenticationService;

    @PostMapping("/getToken")
    public ResponseEntity<?> getToken(@Valid @RequestParam String email){
        ApiResponse response = signUpService.requestSignUp(email);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<?> confirmToken(@Valid @RequestParam String email, @RequestParam String token){
        ApiResponse response = signUpService.confirmToken(email, token);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){
        ApiResponse response = signUpService.completeSignup(signUpRequest);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest){
        TokenResponse response = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok().body(response);
    }
}
