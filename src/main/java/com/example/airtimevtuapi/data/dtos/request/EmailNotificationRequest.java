package com.example.airtimevtuapi.data.dtos.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailNotificationRequest {

    private final Sender sender = new Sender("Xpress Account", "noreply@xpresspayments.com");
    private List<Recipient> to = new ArrayList<>();
    private final String subject = "Welcome to Xpress";
    private String htmlContent;
}
