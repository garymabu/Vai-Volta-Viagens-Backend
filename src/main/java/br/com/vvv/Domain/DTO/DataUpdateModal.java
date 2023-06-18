package br.com.vvv.Domain.DTO;

public record DataUpdateModal(
    String id,
    String type,
    String code,
    String model,
    String capacity,
    String manufactureYear,
    String companyName,
    boolean active
) {
    
}