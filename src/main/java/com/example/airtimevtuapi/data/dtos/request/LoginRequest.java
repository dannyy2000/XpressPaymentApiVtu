package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    @Email(message = "Invalid email address")
    private String email;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String password;
}
