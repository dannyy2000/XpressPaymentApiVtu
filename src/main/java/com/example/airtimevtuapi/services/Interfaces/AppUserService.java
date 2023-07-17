package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.models.AppUser;

public interface AppUserService {

    void saveUser(AppUser appUser);

    AppUser findUserByEmail(String email);


}
