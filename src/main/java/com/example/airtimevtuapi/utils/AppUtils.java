package com.example.airtimevtuapi.utils;

import com.example.airtimevtuapi.data.dtos.request.EmailNotificationRequest;
import com.example.airtimevtuapi.data.dtos.request.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;

@Component
public class AppUtils {
    @Autowired
    private TemplateEngine templateEngine;

//    public static String generateToken(int length) {
//        SecureRandom random = new SecureRandom();
//        int maxNumber = (int) (Math.pow(10, length) - 1);
//        // Generates a random number within the specified length
//        return String.valueOf(random.nextInt(maxNumber));
//    }

    public EmailNotificationRequest buildAuthMail(String email, String token) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        // Adds the email recipient to the request
        request.getTo().add(new Recipient(email));

        // Creates a context for email template processing
        Context context = new Context();
        context.setVariables(Map.of("email", email, "token", token));

        // Processes the "Activate" template using the provided context
        String content = templateEngine.process("Activate", context);

        // Sets the HTML content of the email request
        request.setHtmlContent(content);
        return request;
    }

    public static String generateToken(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);

    }

    public static String calculateHMAC512(String data, String key) {
        String HMAC_SHA512 = "HmacSHA512";

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    HMAC_SHA512
            );
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            return String.valueOf(
                    Hex.encode(
                            mac.doFinal(
                                    data.getBytes(StandardCharsets.UTF_8)
                            )
                    )
            );
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());

        }
    }
}
