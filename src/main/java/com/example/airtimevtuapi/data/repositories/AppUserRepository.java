package com.example.airtimevtuapi.data.repositories;

import com.example.airtimevtuapi.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    Optional <AppUser> findByEmail(String email);

    AppUser findUserByEmail(String email);

    AppUser findUserById(Long id);
}
