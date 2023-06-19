package br.com.vvv.Domain.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataRegisterTrip(
        @NotNull
        LocalDateTime arrivalDatetime,
        @NotNull
        LocalDateTime departureDatetime,
        @NotBlank
        String arrivalLocalizationId,
        @NotBlank
        String departureLocalizationId,
        @NotNull
        @DecimalMin("0.0")
        Float tripValue
) {
}
