package com.example.airtimevtuapi.data.dtos.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirtimePaymentResponse {

     private String requestId;
     private String referenceId;
     private String responseCode;
     private String responseMessage;
     private Object data;
}
