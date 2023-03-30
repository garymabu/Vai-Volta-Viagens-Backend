package br.com.vvv.Domain.dto;

public record DataRegisterClient(
    String name,
    Integer age,
    String login,
    String password,
    String cpf,
    String profession,
    String tell
) {
    
}