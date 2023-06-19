package br.com.vvv.Domain.DTO;


import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Domain.Entity.Trip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataProfileTrip(
        String id,
        LocalDateTime arrivalDatetime,
        LocalDateTime departureDatetime,
        DataProfileLocalization arrivalLocalization,
        DataProfileLocalization departureLocalization
) {
    public DataProfileTrip(Trip trip) {
        this(
            trip.getId(),
            trip.getArrivalDatetime(),
            trip.getDepartureDatetime(),
            new DataProfileLocalization(trip.getArrivalLocalization()),
            new DataProfileLocalization(trip.getDepartureLocalization())
        );
    }
}
