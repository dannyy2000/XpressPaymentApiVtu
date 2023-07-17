package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.common.ApiResponse;
import com.example.airtimevtuapi.data.dtos.request.SignUpRequest;
import org.springframework.transaction.annotation.Transactional;

public interface SignUpService {

    ApiResponse requestSignUp(String email);


    ApiResponse confirmToken(String email,String token);

    ApiResponse completeSignup (SignUpRequest request);
}
