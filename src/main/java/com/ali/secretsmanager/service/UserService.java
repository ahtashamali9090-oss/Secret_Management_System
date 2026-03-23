package com.ali.secretsmanager.service;

import com.ali.secretsmanager.entity.User;
import com.ali.secretsmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ali.secretsmanager.exception.DuplicateResourceException;
import com.ali.secretsmanager.exception.ResourceNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new DuplicateResourceException("Email is already registered.");
        }

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    public User loginUser(String email, String passwordHash) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found.");
        }

        User user = optionalUser.get();

        if (!user.getPasswordHash().equals(passwordHash)) {
            throw new RuntimeException("Invalid password.");
        }

        return user;
    }
}