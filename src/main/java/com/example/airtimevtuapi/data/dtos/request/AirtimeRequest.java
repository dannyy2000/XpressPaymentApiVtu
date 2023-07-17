package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirtimeRequest {

    private int requestId;
    private String uniqueCode;
    private Details details;
}
