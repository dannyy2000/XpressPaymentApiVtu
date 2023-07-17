package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.dtos.request.AirtimeRequest;
import com.example.airtimevtuapi.data.dtos.response.AirtimePurchaseResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;
import org.modelmapper.ModelMapper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.CompletableFuture;

@Service
public class AirtimePaymentImpl {

    @Async
    public CompletableFuture<AirtimePurchaseResponse> buyAirtime(AirtimeRequest airtimeRequest) throws IOException {
        String jsonString = new ObjectMapper().writeValueAsString(airtimeRequest);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        String PaymentHash = calculateHMAC512(jsonString, "7PTM6Ffd6BA7mjgYPodjUd5RK2RXbOUp_CVASPRV");
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonString);


        Request request = new Request.Builder()
                .url("https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil")
                .method("POST", body)
                .addHeader("Authorization", "Bearer KWy7vMYOt6XNZEm2Ed0IA0psAVhn1WMN_CVASPUB")
                .addHeader("PaymentHash", PaymentHash)
                .addHeader("channel", "api")
                .build();
        Response response = client.newCall(request).execute();
//        Response response = client.newCall(request).execute();

        return CompletableFuture.completedFuture(
                new ModelMapper().map(response.body(),
                        AirtimePurchaseResponse.class)
        );
    }
//@Async
//public Object buyAirtime(AirtimeRequest airtimeRequest) throws IOException {
//    String jsonString = new ObjectMapper().writeValueAsString(airtimeRequest);
//    OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//    String PaymentHash = calculateHMAC512(jsonString, "7PTM6Ffd6BA7mjgYPodjUd5RK2RXbOUp_CVASPRV");
//    MediaType mediaType = MediaType.parse("application/json");
//    RequestBody body = RequestBody.create(mediaType, jsonString);
//
//
//    Request request = new Request.Builder()
//            .url("https://billerstest.xpresspayments.com:9603/api/v1/airtime/fulfil")
//            .method("POST", body)
//            .addHeader("Authorization", "Bearer KWy7vMYOt6XNZEm2Ed0IA0psAVhn1WMN_CVASPUB")
//            .addHeader("PaymentHash", PaymentHash)
//            .addHeader("channel", "api")
//            .build();
//    System.out.println("\n\n then reach here    \n\n");
//    Response response = client.newCall(request).execute();
//   /// mapre res = response.
//    System.out.println("\n\n then finally reach here    \n\n with the response"+ response);
//
//    return  response.body();
////        return CompletableFuture.completedFuture(
////                new ModelMapper().map(response.body(),
////                        AirtimePurchaseResponse.class)
////        );
//}

    public static String calculateHMAC512(String data, String key) {
        String HMAC_SHA512 = "HmacSHA512";

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    HMAC_SHA512
            );
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            return String.valueOf(
                    Hex.encode(
                            mac.doFinal(
                                    data.getBytes(StandardCharsets.UTF_8)
                            )
                    )
            );
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
    }
}
