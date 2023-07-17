package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirtimeRequestDto {

    private Long id;
    private BigDecimal amount;
    private String phoneNumber;


}
