package br.com.vvv.Domain.DTO;

public record DataUpdateEmployee(
        String id,
        String name,
        String address,
        String type,
        String login,
        String password
) {}
