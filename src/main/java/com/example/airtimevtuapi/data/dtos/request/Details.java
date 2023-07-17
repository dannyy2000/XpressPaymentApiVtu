package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Details {

    private String phoneNumber;
    private int amount;
}
