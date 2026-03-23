package com.ali.secretsmanager.controller;

import com.ali.secretsmanager.dto.SecretRequestDto;
import com.ali.secretsmanager.dto.SecretResponseDto;
import com.ali.secretsmanager.entity.Secret;
import com.ali.secretsmanager.service.SecretService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/secrets")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/{userId}")
    public SecretResponseDto addSecret(@PathVariable Long userId, @Valid @RequestBody SecretRequestDto secretRequestDto) {
        Secret secret = Secret.builder()
                .serviceName(secretRequestDto.getServiceName())
                .loginUsername(secretRequestDto.getLoginUsername())
                .secretValue(secretRequestDto.getSecretValue())
                .category(secretRequestDto.getCategory())
                .notes(secretRequestDto.getNotes())
                .build();

        return secretService.mapToResponseDto(secretService.addSecret(userId, secret));
    }

    @GetMapping
    public List<SecretResponseDto> getAllSecrets() {
        return secretService.getAllSecrets()
                .stream()
                .map(secretService::mapToResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    public SecretResponseDto getSecretById(@PathVariable Long id) {
        return secretService.mapToResponseDto(secretService.getSecretById(id));
    }

    @PutMapping("/{id}")
    public SecretResponseDto updateSecret(@PathVariable Long id, @Valid @RequestBody SecretRequestDto secretRequestDto) {
        Secret updatedSecret = Secret.builder()
                .serviceName(secretRequestDto.getServiceName())
                .loginUsername(secretRequestDto.getLoginUsername())
                .secretValue(secretRequestDto.getSecretValue())
                .category(secretRequestDto.getCategory())
                .notes(secretRequestDto.getNotes())
                .build();

        return secretService.mapToResponseDto(secretService.updateSecret(id, updatedSecret));
    }

    @DeleteMapping("/{id}")
    public String deleteSecret(@PathVariable Long id) {
        secretService.deleteSecret(id);
        return "Secret deleted successfully.";
    }

    @GetMapping("/search/service")
    public List<SecretResponseDto> searchByServiceName(@RequestParam String serviceName) {
        return secretService.searchByServiceName(serviceName)
                .stream()
                .map(secretService::mapToResponseDto)
                .toList();
    }

    @GetMapping("/search/category")
    public List<SecretResponseDto> searchByCategory(@RequestParam String category) {
        return secretService.searchByCategory(category)
                .stream()
                .map(secretService::mapToResponseDto)
                .toList();
    }
}