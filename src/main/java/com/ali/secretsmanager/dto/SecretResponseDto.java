package com.ali.secretsmanager.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecretResponseDto {

    private Long id;
    private String serviceName;
    private String loginUsername;
    private String secretValue;
    private String category;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long userId;
    private String userName;
    private String userEmail;
}