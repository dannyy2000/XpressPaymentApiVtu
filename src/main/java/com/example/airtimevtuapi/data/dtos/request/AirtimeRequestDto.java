package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirtimeRequestDto {

    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private Long id;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private BigDecimal amount;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String phoneNumber;


}
