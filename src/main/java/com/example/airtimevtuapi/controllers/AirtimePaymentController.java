package com.example.airtimevtuapi.controllers;

import com.example.airtimevtuapi.data.dtos.request.AirtimeRequest;
import com.example.airtimevtuapi.data.dtos.request.LoginRequest;
import com.example.airtimevtuapi.data.dtos.response.AirtimePurchaseResponse;
import com.example.airtimevtuapi.data.dtos.response.TokenResponse;
import com.example.airtimevtuapi.services.Implementations.AirtimePaymentImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/airtime")
public class AirtimePaymentController {

    private final AirtimePaymentImpl airtimePayment;

  //  @PostMapping("https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil")
    @PostMapping()
    public ResponseEntity<?> authenticate(@RequestBody AirtimeRequest airtimeRequest) throws IOException {
        CompletableFuture<AirtimePurchaseResponse> response = airtimePayment.buyAirtime(airtimeRequest);
//        System.out.println("\n \n request first reach here \n\n");
//        Object response = airtimePayment.buyAirtime(airtimeRequest);

        return ResponseEntity.ok().body(response);
    }
}
