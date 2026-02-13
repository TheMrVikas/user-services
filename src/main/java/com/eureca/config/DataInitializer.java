package com.eureca.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.eureca.entity.UserLoginEntity;
import com.eureca.repository.UserLoginRepository;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer {

    private final UserLoginRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (userRepository.count() == 0) {
            UserLoginEntity user = new UserLoginEntity();
            user.setUserName("admin");
            user.setPazzword(passwordEncoder.encode("1234"));
            userRepository.save(user);

            System.out.println("DEFAULT USER CREATED -> admin / 1234");
        }
    }
}