package br.com.vvv.Domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterClient(
    @NotBlank
    String name,

    @NotNull
    Integer age,

    @NotBlank
    String login,

    @NotBlank
    String password,

    @NotBlank
    String cpf,

    @NotBlank
    String profession,

    @NotBlank
    String tell
) {
    
}