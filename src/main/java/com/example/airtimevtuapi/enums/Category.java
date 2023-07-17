package com.example.airtimevtuapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    MTN ("MTN_24207"),

    GLO ("GLO_30387"),

    AIRTEL("AIRTEL_22689"),

    ETISALAT ("9MOBILE_69358");

    private String value;

}
