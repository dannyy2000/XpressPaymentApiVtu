package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.dtos.request.ApiRequestDto;
import com.example.airtimevtuapi.data.dtos.request.AirtimeRequestDto;
import com.example.airtimevtuapi.data.dtos.request.Details;
import com.example.airtimevtuapi.data.dtos.response.AirtimePaymentResponse;

import com.example.airtimevtuapi.data.models.AirtimePayment;
import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.models.Transaction;
import com.example.airtimevtuapi.data.repositories.AirtimePaymentRepository;
import com.example.airtimevtuapi.enums.NetworkProvider;
import com.example.airtimevtuapi.exceptions.CustomException;
import com.example.airtimevtuapi.exceptions.UserNotFoundException;
import com.example.airtimevtuapi.services.Interfaces.AppUserService;
import com.example.airtimevtuapi.services.Interfaces.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import okhttp3.*;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

import java.time.LocalDateTime;

import static com.example.airtimevtuapi.common.Message.USER_NOT_FOUND;
import static com.example.airtimevtuapi.utils.AppUtils.calculateHMAC512;

@Service
@RequiredArgsConstructor
public class AirtimePaymentService {

    private final TransactionService transactionService;
    private final AppUserService appUserService;

    private final AirtimePaymentRepository airtimePaymentRepository;


    public AirtimePaymentResponse requestAirtime(AirtimeRequestDto airtimeRequestDto) throws IOException {
        AppUser appUser = appUserService.findUserById(airtimeRequestDto.getId());
        if(appUser != null) {
            BigDecimal amount = airtimeRequestDto.getAmount();
            AirtimePayment airtimePayment = mapAirtimePayment(airtimeRequestDto, appUser, amount);
            AirtimePayment savedAirtime = saveAirtimePurchase(airtimePayment);

            ApiRequestDto airtimeRequest = createAirtimePayment(savedAirtime);
            Transaction transaction = buildTransaction(appUser, amount);
            saveTransaction(transaction);
            return getAirtime(airtimeRequest);
        }
        throw new UserNotFoundException(USER_NOT_FOUND);

    }


    private static ApiRequestDto createAirtimePayment(AirtimePayment savedAirtime) {
        String phoneNumber = savedAirtime.getPhoneNumber();
        Details details = Details.builder()
                .amount(savedAirtime.getAmount())
                .phoneNumber(phoneNumber)
                .build();

        return ApiRequestDto.builder()
                .requestId(savedAirtime.getId())
                .uniqueCode(uniqueCode(phoneNumber))
                .details(details)
                .build();

    }

    private AirtimePayment mapAirtimePayment(AirtimeRequestDto airtimeRequestDto, AppUser appUser, BigDecimal amount) {
        return AirtimePayment.builder()
                .phoneNumber(airtimeRequestDto.getPhoneNumber())
                .amount(amount)
                .appUser(appUser)
                .uniqueCode(uniqueCode(airtimeRequestDto.getPhoneNumber()))
                .time(LocalDateTime.now())
                .build();
    }

    private AirtimePayment saveAirtimePurchase(AirtimePayment airtimePayment) {
        return airtimePaymentRepository.save(airtimePayment);
    }

    private static String uniqueCode(String phoneNumber) {
       return NetworkProvider.getProvider(phoneNumber);
    }

    public AirtimePaymentResponse getAirtime(ApiRequestDto apiRequestDto) throws IOException {
        String jsonString = new ObjectMapper().writeValueAsString(apiRequestDto);
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
