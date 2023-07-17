package com.example.airtimevtuapi.config.security.service;



import com.example.airtimevtuapi.config.security.user.SecureUser;
import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.repositories.AppUserRepository;
import com.example.airtimevtuapi.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the user in the repository based on the email
        AppUser user = repository.findUserByEmail(email);
        if (user == null) {
            // Throw a UsernameNotFoundException if the user is not found
            throw new UsernameNotFoundException("Not found");
        }
        // Create and return a SecureUser object with the user details and roles
        return SecureUser.builder()
                .user(user)
                .roles (List.of (Role.CUSTOMER, Role.ADMIN))
                .build();
    }
}

