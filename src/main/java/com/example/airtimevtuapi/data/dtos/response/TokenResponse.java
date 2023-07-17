package com.example.airtimevtuapi.data.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {

    String accessToken;
    String refreshToken;
}
