package com.example.airtimevtuapi.config.mail;

import com.example.airtimevtuapi.data.dtos.request.EmailNotificationRequest;

public interface MailService {

   String sendHtmlMail(EmailNotificationRequest emailNotificationRequest);
}
