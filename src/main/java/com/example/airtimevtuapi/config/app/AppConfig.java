package com.example.airtimevtuapi.config.app;

import com.example.airtimevtuapi.config.mail.MailConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class AppConfig {

    @Value("${mail.api.key}")
    private String mailApiKey;
    @Value("${sendinblue.mail.url}")
    private String mailUrl;

    @Bean
    public MailConfiguration mailConfiguration(){
        // Creates and returns a new MailConfiguration object with the provided mailApiKey and mailUrl
        return new MailConfiguration(mailApiKey,mailUrl);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // Creates and returns a new BCryptPasswordEncoder object for password encoding
        return new BCryptPasswordEncoder();
    }

}
