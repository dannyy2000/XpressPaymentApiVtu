package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String firstName;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String lastName;
    @Email(message = "Invalid email address")
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String email;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String password;
}
