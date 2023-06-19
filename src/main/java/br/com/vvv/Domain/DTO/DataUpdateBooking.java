package br.com.vvv.Domain.DTO;

import java.math.BigDecimal;

public record DataUpdateBooking(
    Integer number,
    Integer timeTrip,
    String typeId,
    String code,
    boolean layover,
    String createDate,
    boolean status,
    BigDecimal value,
    String originId,
    String arrivalLocalization,
    String departureLocalization,
    String clientId,
    String employeeId
) {

}
