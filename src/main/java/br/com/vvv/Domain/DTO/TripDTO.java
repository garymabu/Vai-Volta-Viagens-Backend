package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Localization;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record TripDTO(
        LocalDateTime arrivalDatetime,
        LocalDateTime departureDatetime,
        Localization arrivalLocalization,
        Localization departureLocalization
) {
}
