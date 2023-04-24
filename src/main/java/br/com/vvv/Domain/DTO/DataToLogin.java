package br.com.vvv.Domain.DTO;

import jakarta.validation.constraints.NotBlank;

public record DataToLogin(
    @NotBlank
    String login,
    @NotBlank
    String password
) { }