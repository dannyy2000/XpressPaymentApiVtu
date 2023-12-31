package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Details {

    private String phoneNumber;
    private BigDecimal amount;
}
