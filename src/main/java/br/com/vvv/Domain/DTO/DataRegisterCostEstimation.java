package br.com.vvv.Domain.DTO;

import java.util.List;

public record DataRegisterCostEstimation(
        List<DataRegisterReservationParticipant> participantList,
        List<String> trips
) {
}
