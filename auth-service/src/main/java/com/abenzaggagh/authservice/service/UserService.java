package com.abenzaggagh.authservice.service;

import com.abenzaggagh.authservice.dto.LoginRequestDTO;
import com.abenzaggagh.authservice.model.User;
import com.abenzaggagh.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
