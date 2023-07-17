package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.dtos.request.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    private LoginRequest loginRequest;

    @BeforeEach
     void testComplete(){
        loginRequest = new LoginRequest (
           "rurdonopsoo@gufum.com",
                "Akinsan2020"
        );
    }

    @Test
    void signIn(){
       var response =  authenticationService.authenticate(loginRequest);
        assertThat(response.getAccessToken()).isNotNull();
        assertThat(response.getRefreshToken()).isNotNull();

    }

}