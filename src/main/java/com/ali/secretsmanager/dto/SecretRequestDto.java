package com.ali.secretsmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecretRequestDto {

    @NotBlank(message = "Service name is required.")
    private String serviceName;

    @NotBlank(message = "Login username is required.")
    private String loginUsername;

    @NotBlank(message = "Secret value is required.")
    private String secretValue;

    private String category;
    private String notes;
}