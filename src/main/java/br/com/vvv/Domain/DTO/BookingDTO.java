package br.com.vvv.Domain.DTO;

import java.math.BigDecimal;

import br.com.vvv.Domain.Entity.Client;
import br.com.vvv.Domain.Entity.Employee;
import br.com.vvv.Domain.Entity.Localization;
import br.com.vvv.Domain.Entity.Status;
import br.com.vvv.Domain.Entity.TypeBooking;

public record BookingDTO(
    Integer number, 
    Integer timeTrip, 
    TypeBooking typeId, 
    String code, 
    boolean layover,                
    String createDate, 
    Status statusId, 
    BigDecimal value, 
    Localization arrivalLocalizationId,              
    Localization departureLocalizationId, 
    Client clientId, 
    Employee employeeId
) {

}
