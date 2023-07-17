package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
}
