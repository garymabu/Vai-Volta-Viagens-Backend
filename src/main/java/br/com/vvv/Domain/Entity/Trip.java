package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.*;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "trip")
@Table(name = "trip")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    private String id;

    private LocalDateTime arrivalDatetime;
    private LocalDateTime departureDatetime;
    @ManyToOne
    @JoinColumn(name = "arrival_localization_id", referencedColumnName = "id")
    private Localization arrivalLocalization;

    @ManyToOne
    @JoinColumn(name = "departure_localization_id", referencedColumnName = "id")
    private Localization departureLocalization;

    public Trip(TripDTO dataRegisterTrip) {
        this.id = DataHelper.generatedUuid().toString();
        this.arrivalDatetime = dataRegisterTrip.arrivalDatetime();
        this.departureDatetime = dataRegisterTrip.departureDatetime();
        this.arrivalLocalization = dataRegisterTrip.arrivalLocalization();
        this.departureLocalization = dataRegisterTrip.departureLocalization();
    }

    public void updateData(String id, TripDTO dataRegisterTrip) {
        this.arrivalDatetime = dataRegisterTrip.arrivalDatetime();
        this.departureDatetime = dataRegisterTrip.departureDatetime();
        this.arrivalLocalization = dataRegisterTrip.arrivalLocalization();
        this.departureLocalization = dataRegisterTrip.departureLocalization();
    }
}
