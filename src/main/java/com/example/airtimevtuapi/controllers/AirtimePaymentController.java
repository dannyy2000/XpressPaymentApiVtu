package com.example.airtimevtuapi.controllers;

import com.example.airtimevtuapi.data.dtos.request.AirtimeRequest;
import com.example.airtimevtuapi.data.dtos.request.AirtimeRequestDto;
import com.example.airtimevtuapi.data.dtos.response.AirtimePaymentResponse;
import com.example.airtimevtuapi.services.Implementations.AirtimePaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
//import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@RequestMapping("/airtime")
public class AirtimePaymentController {

    private final AirtimePaymentService airtimePayment;

    @PostMapping()
    public ResponseEntity<AirtimePaymentResponse> buyAirtime(
            @RequestBody AirtimeRequestDto airtimeRequest) throws IOException {
        return ResponseEntity.ok(airtimePayment.requestAirtime(airtimeRequest));
               }
}
