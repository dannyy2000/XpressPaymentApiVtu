package com.example.airtimevtuapi.data.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiRequestDto {

   @NotNull(message = "This field cannot be null")
   @NotBlank(message = "This field cannot be blank")
    private String requestId;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private String uniqueCode;
    @NotNull(message = "This field cannot be null")
    @NotBlank(message = "This field cannot be blank")
    private Details details;
}
