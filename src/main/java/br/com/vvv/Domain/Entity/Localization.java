package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterEmployee;
import br.com.vvv.Domain.DTO.DataRegisterModal;
import br.com.vvv.Domain.DTO.DataRegisterTrip;
import br.com.vvv.Domain.DTO.DataUpdateModal;
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

@Entity(name = "localization")
@Table(name = "localization")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Localization {
    @Id
    private String id;
    private String cityId;
    private String airportCode;
    private String airportName;
}
