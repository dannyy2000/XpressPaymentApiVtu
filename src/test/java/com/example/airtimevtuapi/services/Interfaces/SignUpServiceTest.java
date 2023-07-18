package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.dtos.request.SignUpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SignUpServiceTest {

    @Autowired
    private SignUpService service;



    @Test
    public void requestSignUp() {
        String email = "tatrulufye@gufum.com";
        var response = service.requestSignUp(email);
        assertThat(response.isSuccess()).isTrue();
    }



    @Test
    void completeSignup() {
        SignUpRequest request = new SignUpRequest();
        request.setEmail("lurtadeknu@gufum.com");
        request.setFirstName("Tomi");
        request.setLastName("Akinsanya");
        request.setPassword("Akinsanya2020");
        var result = service.completeSignup (request);
        assertThat (result.isSuccess ()).isTrue ();
        assertThat (result).isNotNull ();
    }
}

