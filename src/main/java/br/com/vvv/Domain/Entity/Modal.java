package br.com.vvv.Domain.Entity;

import br.com.vvv.Domain.DTO.DataRegisterModal;
import br.com.vvv.Domain.DTO.DataUpdateModal;
import br.com.vvv.Helpers.DataHelper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "modal")
@Table(name = "modal")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Modal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String type;
    private String code;
    private String model;
    private String capacity;
    private String yearManufacture;
    private String companyName;
    private Boolean active;

    public Modal(DataRegisterModal dataRegisterClient) {
        this.id = DataHelper.generatedUuid().toString();
        this.type = dataRegisterClient.type();
        this.code = dataRegisterClient.code();
        this.model = dataRegisterClient.model();
        this.capacity = dataRegisterClient.capacity();
        this.companyName = dataRegisterClient.companyName();
        this.yearManufacture = dataRegisterClient.manufactureYear();
        this.active = dataRegisterClient.active();
    }

    public void updateData(DataUpdateModal updatedModalData) {
        this.type = updatedModalData.type();
        this.code = updatedModalData.code();
        this.model = updatedModalData.model();
        this.capacity = updatedModalData.capacity();
        this.companyName = updatedModalData.companyName();
        this.yearManufacture = updatedModalData.manufactureYear();
        this.active = updatedModalData.active();
    }

}