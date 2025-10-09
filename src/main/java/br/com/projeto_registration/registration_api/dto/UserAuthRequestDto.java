package br.com.projeto_registration.registration_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthRequestDto(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotBlank String cpf,
        @NotBlank String phone
) {
}
