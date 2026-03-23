package com.ali.secretsmanager.service;
import com.ali.secretsmanager.entity.Secret;
import com.ali.secretsmanager.entity.User;
import com.ali.secretsmanager.repository.SecretRepository;
import com.ali.secretsmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.ali.secretsmanager.exception.ResourceNotFoundException;
import com.ali.secretsmanager.dto.SecretResponseDto;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecretService {

    private final SecretRepository secretRepository;
    private final UserRepository userRepository;

    public SecretService(SecretRepository secretRepository, UserRepository userRepository) {
        this.secretRepository = secretRepository;
        this.userRepository = userRepository;
    }

    public Secret addSecret(Long userId, Secret secret) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("User not found.");
        }

        User user = optionalUser.get();

        secret.setUser(user);
        secret.setCreatedAt(LocalDateTime.now());
        secret.setUpdatedAt(LocalDateTime.now());

        return secretRepository.save(secret);
    }

    public List<Secret> getAllSecrets() {
        return secretRepository.findAll();
    }

    public Secret getSecretById(Long id) {
        Optional<Secret> optionalSecret = secretRepository.findById(id);

        if (optionalSecret.isEmpty()) {
            throw new ResourceNotFoundException("Secret not found.");
        }

        return optionalSecret.get();
    }

    public Secret updateSecret(Long id, Secret updatedSecret) {
        Optional<Secret> optionalSecret = secretRepository.findById(id);

        if (optionalSecret.isEmpty()) {
            throw new ResourceNotFoundException("Secret not found.");
        }

        Secret existingSecret = optionalSecret.get();

        existingSecret.setServiceName(updatedSecret.getServiceName());
        existingSecret.setLoginUsername(updatedSecret.getLoginUsername());
        existingSecret.setSecretValue(updatedSecret.getSecretValue());
        existingSecret.setCategory(updatedSecret.getCategory());
        existingSecret.setNotes(updatedSecret.getNotes());
        existingSecret.setUpdatedAt(LocalDateTime.now());

        return secretRepository.save(existingSecret);
    }

    public void deleteSecret(Long id) {
        Optional<Secret> optionalSecret = secretRepository.findById(id);

        if (optionalSecret.isEmpty()) {
            throw new ResourceNotFoundException("Secret not found.");
        }

        Secret secret = optionalSecret.get();
        secretRepository.delete(secret);
    }

    public List<Secret> searchByServiceName(String serviceName) {
        return secretRepository.findByServiceNameContainingIgnoreCase(serviceName);
    }

    public List<Secret> searchByCategory(String category) {
        return secretRepository.findByCategoryContainingIgnoreCase(category);
    }
    public SecretResponseDto mapToResponseDto(Secret secret) {
        return SecretResponseDto.builder()
                .id(secret.getId())
                .serviceName(secret.getServiceName())
                .loginUsername(secret.getLoginUsername())
                .secretValue(secret.getSecretValue())
                .category(secret.getCategory())
                .notes(secret.getNotes())
                .createdAt(secret.getCreatedAt())
                .updatedAt(secret.getUpdatedAt())
                .userId(secret.getUser().getId())
                .userName(secret.getUser().getName())
                .userEmail(secret.getUser().getEmail())
                .build();
    }
}

