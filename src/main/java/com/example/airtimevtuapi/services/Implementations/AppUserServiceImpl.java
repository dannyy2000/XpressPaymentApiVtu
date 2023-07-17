package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.repositories.AppUserRepository;
import com.example.airtimevtuapi.exceptions.CustomException;
import com.example.airtimevtuapi.services.Interfaces.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.airtimevtuapi.common.Message.EMAIL_NOT_FOUND;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    // Constructor or dependency injection is expected to initialize appUserRepository.


    @Override
    public void saveUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }
    // This method saves the given AppUser object using the appUserRepository.

    @Override
    public AppUser findUserByEmail(String email) {
        return appUserRepository.findUserByEmail(email);
    }
    // This method retrieves an AppUser object from the appUserRepository based on the provided email.
}
