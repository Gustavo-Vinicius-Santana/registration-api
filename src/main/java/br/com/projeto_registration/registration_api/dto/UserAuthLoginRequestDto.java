package br.com.projeto_registration.registration_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthLoginRequestDto(
        @NotBlank @Email String email,
        @NotBlank String password
) {
}
