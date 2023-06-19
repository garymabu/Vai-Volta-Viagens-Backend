package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterLocalization;
import br.com.vvv.Domain.DTO.DataUpdateLocalization;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "localization")
@Table(name = "localization")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Localization {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cityId;
    private String airportCode;
    private String airportName;
    private String cityName;

    public Localization(DataRegisterLocalization dataRegisterLocalization) {
        this.id = DataHelper.generatedUuid().toString();
        this.cityId = dataRegisterLocalization.cityId();
        this.airportCode = dataRegisterLocalization.airportCode();
        this.airportName = dataRegisterLocalization.airportName();
        this.cityName = dataRegisterLocalization.cityName();
    }

    public void updateData(DataUpdateLocalization dataUpdateLocalization) {
        if (dataUpdateLocalization.cityId() != null) {
            this.cityId = dataUpdateLocalization.cityId();
        }

        if (dataUpdateLocalization.airportCode() != null) {
            this.airportCode = dataUpdateLocalization.airportCode();
        }

        if (dataUpdateLocalization.airportName() != null) {
            this.airportName = dataUpdateLocalization.airportName();
        }

        if (dataUpdateLocalization.cityName() != null) {
            this.cityName = dataUpdateLocalization.cityName();
        }

        if (dataUpdateLocalization.cityId() != null && dataUpdateLocalization.airportCode() != null &&
                dataUpdateLocalization.airportName() != null && dataUpdateLocalization.cityName() != null) {
            throw new IllegalStateException("É necessário fornecer pelo menos um valor para a atualização.");
        }
    }
}
