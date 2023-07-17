package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.models.AuthToken;
import com.example.airtimevtuapi.data.repositories.AuthTokenRepository;
import com.example.airtimevtuapi.exceptions.TokenValidationException;
import com.example.airtimevtuapi.services.Interfaces.AuthTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.airtimevtuapi.common.Message.AUTH_TOKEN_NOT_VALID;

@Service
@Slf4j
@AllArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService {

    private final AuthTokenRepository authTokenRepository;
    @Override
    public void saveAuthToken(AuthToken token) {
        authTokenRepository.save(token);
    }
    // This method saves the given AuthToken object using the authTokenRepository.

    @Override
    public Optional<AuthToken> checkToken(String token) {
        return authTokenRepository.findByToken(token);
    }
    // This method retrieves an AuthToken object from the authTokenRepository based on the provided token.

    @Override
    public void setConfirmedAt(String token) {
        // Checks if the token is valid
        if(isValidToken(token)){
            // Retrieves the AuthToken object associated with the token
            var getToken = authTokenRepository.findAuthByToken(token);
            log.info("{}", getToken);
            // Sets the confirmedAt field of the AuthToken to the current LocalDateTime
            getToken.ifPresent (authToken -> authToken.setConfirmedAt (LocalDateTime.now ()));
            // Saves the updated AuthToken
            saveAuthToken (getToken.get());
            // Throws a TokenValidationException if the token is not valid
        }else throw new TokenValidationException(AUTH_TOKEN_NOT_VALID);
        }



    private boolean isValidToken(String token){
        // Checks if the AuthToken object associated with the token exists in the repository
        return authTokenRepository.findAuthByToken(token).isPresent();
    }
}
