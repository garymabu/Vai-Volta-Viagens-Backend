package br.com.vvv.Domain.DTO;

import jakarta.validation.constraints.NotBlank;


public record DataRegisterEmployee(
        @NotBlank
        String name,

        @NotBlank
        String login,

        @NotBlank
        String password,

        @NotBlank
        String address,

        @NotBlank
        String type
) {

}
