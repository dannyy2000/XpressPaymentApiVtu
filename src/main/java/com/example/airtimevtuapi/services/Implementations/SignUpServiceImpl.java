package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.common.ApiResponse;
import com.example.airtimevtuapi.config.mail.MailService;
import com.example.airtimevtuapi.data.dtos.request.EmailNotificationRequest;
import com.example.airtimevtuapi.data.dtos.request.SignUpRequest;
import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.models.AuthToken;
import com.example.airtimevtuapi.data.repositories.AuthTokenRepository;
import com.example.airtimevtuapi.enums.Role;
import com.example.airtimevtuapi.exceptions.CustomException;
import com.example.airtimevtuapi.exceptions.TokenExpiredException;
import com.example.airtimevtuapi.services.Interfaces.AppUserService;
import com.example.airtimevtuapi.services.Interfaces.AuthTokenService;
import com.example.airtimevtuapi.services.Interfaces.SignUpService;
import com.example.airtimevtuapi.utils.AppUtils;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.example.airtimevtuapi.common.Message.*;
import static com.example.airtimevtuapi.utils.AppUtils.generateToken;
import static com.example.airtimevtuapi.utils.ResponseUtils.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignUpServiceImpl implements SignUpService {
    private final AppUserService service;

    private final AuthTokenService authTokenService;

    private final AppUtils utils;

    private final MailService mailService;

    private final PasswordEncoder encoder;

    @Override
    public ApiResponse requestSignUp(String email) {
        // Checks if the user already exists with the provided email
        AppUser checkUser = service.findUserByEmail(email);

        if(checkUser != null){
            // Throws a CustomException if the email is already found
            throw new CustomException(EMAIL_FOUND);
        }

        // Generates a token with 4 characters
        String token = generateToken(4);

        // Creates an AuthToken object with the generated token, timestamps, and email
        AuthToken geneToken = new AuthToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(20),
                email
        );
        // Saves the AuthToken using the authTokenService
        authTokenService.saveAuthToken(geneToken);

        // Builds an EmailNotificationRequest for authentication mail
        EmailNotificationRequest notificationRequest =
                utils.buildAuthMail(
                        email,
                        geneToken.getToken()
                );
        log.info ("token {}", geneToken.getToken ());
        // Sends the authentication email using the mailService
        String response = mailService.sendHtmlMail(notificationRequest);

        if(response == null){
            return getFailedResponse();
        }
        return getResponse();
    }

    @Override
    public ApiResponse confirmToken(String email, String token) {
        // Verifies the token by checking if it exists in the authTokenService
        AuthToken verifyToken = authTokenService.checkToken(token).orElseThrow(()->new TokenExpiredException(AUTH_TOKEN_NOT_FOUND));
        log.info ("email {} ,token {}", email,token);
        if(verifyToken.getConfirmedAt() != null){
            // Throws a TokenExpiredException if the token is already confirmed
            throw new TokenExpiredException(AUTH_TOKEN_ALREADY_CONFIRMED);
        }
        log.info ("verifyToken {}", verifyToken);
        LocalDateTime expiryTime = verifyToken.getExpiresAt();
        if(expiryTime.isBefore(LocalDateTime.now())){
            // Throws a TokenExpiredException if the token is expired
            throw new TokenExpiredException(AUTH_TOKEN_EXPIRED);
        }
        // Sets the confirmedAt field of the AuthToken to the current LocalDateTime
        authTokenService.setConfirmedAt(token);
        return getConfirmResponse();




    }

    @Override
    public ApiResponse completeSignup(SignUpRequest request) {
        log.info("completing sign up {}",request);
        // Creates an AppUser object with the provided sign-up details
        AppUser registerUser = AppUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .createdAt(LocalDateTime.now())
                .roles(Collections.singletonList(Role.CUSTOMER))
                .build();
        // Saves the user using the service
          service.saveUser(registerUser);
          return getCreatedResponse();

    }
}
