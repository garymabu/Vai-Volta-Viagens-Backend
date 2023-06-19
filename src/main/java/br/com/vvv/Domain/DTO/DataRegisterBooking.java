package br.com.vvv.Domain.DTO;

public record DataRegisterBooking(
    Integer number,
    Integer timeTrip,
    String typeId,
    String code,
    boolean layover,
    String createDate,
    String statusId,
    String value,
    String arrivalLocalizationId,
    String departureLocalizationId,
    String clientId,
    String employeeId
) {

}
