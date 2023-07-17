package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.dtos.request.AirtimeRequest;
import com.example.airtimevtuapi.data.dtos.request.AirtimeRequestDto;
import com.example.airtimevtuapi.data.dtos.request.Details;
import com.example.airtimevtuapi.data.dtos.response.AirtimePaymentResponse;

import com.example.airtimevtuapi.data.models.AirtimePayment;
import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.models.Transaction;
import com.example.airtimevtuapi.data.repositories.AirtimePaymentRepository;
import com.example.airtimevtuapi.enums.Category;
import com.example.airtimevtuapi.exceptions.CustomException;
import com.example.airtimevtuapi.services.Interfaces.AppUserService;
import com.example.airtimevtuapi.services.Interfaces.TransactionService;
import com.example.airtimevtuapi.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import okhttp3.*;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AirtimePaymentService {

    private final AirtimePaymentRepository airtimePaymentRepository;
    private final TransactionService transactionService;
    private final AppUserService appUserService;


    public AirtimePaymentResponse requestAirtime(AirtimeRequestDto airtimeRequestDto) throws IOException {
        AppUser appUser = appUserService.findUserById(airtimeRequestDto.getId());
        BigDecimal amount = airtimeRequestDto.getAmount();
        String uniqueCode = AppUtils.generateToken(10);

        AirtimePayment airtimePayment = mapAirtimePayment(airtimeRequestDto, appUser, amount, uniqueCode);
        AirtimePayment savedAirtime = saveAirtimePurchase(airtimePayment);

        AirtimeRequest airtimeRequest = createAirtimePayment(savedAirtime);
        Transaction transaction = buildTransaction(appUser, amount);
        saveTransaction(transaction);
        return getAirtime(airtimeRequest);


    }


    private static  AirtimeRequest createAirtimePayment(AirtimePayment savedAirtime) {
        String phoneNumber = savedAirtime.getPhoneNumber();
        Details details = Details.builder()
                .amount(savedAirtime.getAmount())
                .phoneNumber(phoneNumber)
                .build();

        return AirtimeRequest.builder()
                .requestId(savedAirtime.getId())
                .uniqueCode(uniqueCode(phoneNumber))
                .details(details)
                .build();

    }

    private AirtimePayment mapAirtimePayment(AirtimeRequestDto airtimeRequestDto, AppUser appUser, BigDecimal amount, String uniqueCode) {
        return AirtimePayment.builder()
                .phoneNumber(airtimeRequestDto.getPhoneNumber())
                .amount(amount)
                .appUser(appUser)
                .uniqueCode(uniqueCode)
                .time(LocalDateTime.now())
                .build();
    }

    private AirtimePayment saveAirtimePurchase(AirtimePayment airtimePayment) {
        return airtimePaymentRepository.save(airtimePayment);
    }

    private static String uniqueCode(String phoneNumber) {
        if (phoneNumber.equals("08033333333")) {
            return Category.MTN.getValue();
        } else if (phoneNumber.equals("08022222222")) {
            return Category.AIRTEL.getValue();
        } else if (phoneNumber.equals("09099999999")) {
            return Category.ETISALAT.getValue();
        } else if (phoneNumber.equals("08055555555")) {
            return Category.GLO.getValue();
        } else {
            throw new CustomException("Code is invalid");
        }
    }

    public AirtimePaymentResponse getAirtime(AirtimeRequest airtimeRequest) throws IOException {
        String jsonString = new ObjectMapper().writeValueAsString(airtimeRequest);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
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

        // OkHttpClient client = new OkHttpClient().newBuilder().build();
        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        assert responseBody != null;
        AirtimePaymentResponse airtimePurchaseResponse = objectMapper.readValue(
                responseBody.string(),
                AirtimePaymentResponse.class
        );

        response.close();
        return airtimePurchaseResponse;

    }
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
    private Transaction buildTransaction(AppUser appUser, BigDecimal amount) {
        return Transaction.builder()
                .appUser(appUser)
                .amount(amount)
                .build();
    }

    private void saveTransaction(Transaction transaction) {
        transactionService.Save(transaction);
    }
}
