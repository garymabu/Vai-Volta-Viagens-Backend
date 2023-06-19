package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Localization;

public record DataLocalizations(
    String id,
    String cityId,
    String airportCode,
    String airportName,
    String cityName
) {
    public DataLocalizations(Localization localization) {
        this(localization.getId(), localization.getCityId(), localization.getAirportCode(), localization.getAirportName(), localization.getCityName());
    }
}
