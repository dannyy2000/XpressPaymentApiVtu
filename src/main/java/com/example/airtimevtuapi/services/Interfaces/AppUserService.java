package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.models.AppUser;

import java.util.Optional;

public interface AppUserService {

    void saveUser(AppUser appUser);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);


}
