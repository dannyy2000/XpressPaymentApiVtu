package com.example.airtimevtuapi.exceptions;

public class AuthenticationException extends IllegalArgumentException{
    public AuthenticationException(String message) {
        super(message);
    }
}
