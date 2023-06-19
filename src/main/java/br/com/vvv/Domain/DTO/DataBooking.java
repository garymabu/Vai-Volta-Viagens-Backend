package br.com.vvv.Domain.DTO;

import java.math.BigDecimal;

import br.com.vvv.Domain.Entity.Booking;

public record DataBooking(
    String id,
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

    public DataBooking(Booking booking) {
        this(
            booking.getId(),
            booking.getNumber(),
            booking.getTimeTrip(),
            booking.getTypeId(),
            booking.getCode(),
            booking.isLayover(),
            booking.getCreateDate(),
            booking.isStatus(),
            booking.getValue(),
            booking.getOriginId(),
            booking.getArrivalLocalization(),
            booking.getDepartureLocalization(),
            booking.getClientId(),
            booking.getEmployeeId()
        );
    }

}
