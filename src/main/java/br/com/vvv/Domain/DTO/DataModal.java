package br.com.vvv.Domain.DTO;

import br.com.vvv.Domain.Entity.Modal;

public record DataModal(
    String id,
    String type,
    String code,
    String model,
    String capacity,
    String manufactureYear,
    String companyName,
    boolean active
) {

    public DataModal(Modal modal) {
        this(modal.getId(), modal.getType(), modal.getModel(), modal.getCode(), modal.getCapacity(), modal.getCompanyName(), modal.getYearManufacture(), modal.getActive());
    }

}
