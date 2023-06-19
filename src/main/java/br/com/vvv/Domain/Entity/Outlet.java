package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterOutlet;
import br.com.vvv.Domain.DTO.DataUpdateOutlet;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "outlet")
@Table(name = "outlet")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Outlet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String location;

    public Outlet(DataRegisterOutlet dataRegisterOutlet) {
        this.id = DataHelper.generatedUuid().toString();
        this.name = dataRegisterOutlet.name();
        this.location = dataRegisterOutlet.location();
    }

    public void updateOutlet(DataUpdateOutlet dataUpdateOutlet) {
        this.name = dataUpdateOutlet.id();
        this.location = dataUpdateOutlet.location();
    }
    
}