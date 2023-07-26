package com.example.airtimevtuapi.config.mail;

import com.example.airtimevtuapi.data.dtos.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MailServiceImplementation implements MailService {

    private final MailConfiguration mailConfiguration;
    @Override
    public String sendHtmlMail(EmailNotificationRequest emailNotificationRequest) {

        RestTemplate restTemplate = new RestTemplate();

        // Set the HTTP headers for the request
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("api-key",mailConfiguration.getApiKey());

        // Create the HTTP entity with the email notification request and headers
        HttpEntity<EmailNotificationRequest> httpEntity = new HttpEntity<>(emailNotificationRequest,httpHeaders);

        // Send a POST request to the mail URL and retrieve the response
        ResponseEntity<String> response =
                restTemplate.postForEntity(mailConfiguration.getMailUrl(),httpEntity, String.class);

        return response.getBody();
    }
}
