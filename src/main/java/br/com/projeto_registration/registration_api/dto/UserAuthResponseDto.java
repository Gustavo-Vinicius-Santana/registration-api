package br.com.projeto_registration.registration_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

import br.com.projeto_registration.registration_api.models.UserAuth;

public record UserAuthResponseDto(
        UUID id,
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String cpf,
        @NotBlank String phone
) {
    public UserAuthResponseDto(UserAuth userAuth) {
        this(
                userAuth.getId(),
                userAuth.getUser() != null ? userAuth.getUser().getName() : null,
                userAuth.getEmail(),
                userAuth.getUser() != null ? userAuth.getUser().getCpf() : null,
                userAuth.getUser() != null ? userAuth.getUser().getPhone() : null
        );
    }
}