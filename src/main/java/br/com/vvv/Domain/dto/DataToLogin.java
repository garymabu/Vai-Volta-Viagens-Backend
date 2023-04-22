package br.com.vvv.Domain.dto;

import jakarta.validation.constraints.NotBlank;

public record DataToLogin(
    @NotBlank
    String login,
    @NotBlank
    String password
) {
    
}