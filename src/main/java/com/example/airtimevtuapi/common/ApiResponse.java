package com.example.airtimevtuapi.common;

import com.example.airtimevtuapi.enums.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ApiResponse {

    private boolean success;
    private ResponseMessage message;
    private int statusCode;
}
