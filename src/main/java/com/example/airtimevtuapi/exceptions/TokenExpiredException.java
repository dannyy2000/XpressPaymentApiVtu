package com.example.airtimevtuapi.exceptions;

public class TokenExpiredException extends CustomException {

    public TokenExpiredException(String message) {
        super(message);
    }
}
