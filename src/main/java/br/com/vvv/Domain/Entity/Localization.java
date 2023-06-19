package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterLocalization;
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

    public Localization(DataRegisterLocalization dataRegisterLocalization) {
        this.id = DataHelper.generatedUuid().toString();
        this.cityId = dataRegisterLocalization.cityId();
        this.airportCode = dataRegisterLocalization.airportCode();
        this.airportName = dataRegisterLocalization.airportName();
    }
}
