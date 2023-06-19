package br.com.vvv.Domain.DTO;
import java.util.List;

public record TravelsWithLayoverDTO(
    List<DataProfileTrip> tripsUntilDestination,
    DataProfileTrip firstTrip,
    DataProfileTrip lastTrip,
    Boolean containsLayover
) {
}
