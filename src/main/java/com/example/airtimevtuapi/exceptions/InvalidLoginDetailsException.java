package com.example.airtimevtuapi.exceptions;

public class InvalidLoginDetailsException extends CustomException{
    public InvalidLoginDetailsException(String message) {
        super(message);
    }
}
