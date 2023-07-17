package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipient {
     @Email
    private String email;

}
