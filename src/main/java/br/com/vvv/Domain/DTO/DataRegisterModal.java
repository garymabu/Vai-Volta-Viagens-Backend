package br.com.vvv.Domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataRegisterModal(
    @NotBlank
    String type,

    @NotBlank
    String code,

    @NotBlank
    String model,

    @NotBlank
    String capacity,

    @NotBlank
    String manufactureYear,

    @NotBlank
    String companyName,

    @NotNull
    boolean active
) {
    
}