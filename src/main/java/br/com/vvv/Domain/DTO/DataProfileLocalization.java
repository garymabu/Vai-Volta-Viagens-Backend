package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Localization;

public record DataProfileLocalization(
    String id,
    String airportName,
    String airportCode,
    String cityId,
    String cityName
) {
    public DataProfileLocalization(Localization localization) {
        this(localization.getId(), localization.getAirportName(), localization.getAirportCode(), localization.getCityId(), localization.getCityName());
    }
}
