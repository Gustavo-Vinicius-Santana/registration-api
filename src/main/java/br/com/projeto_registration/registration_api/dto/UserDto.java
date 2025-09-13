package br.com.projeto_registration.registration_api.dto;

import br.com.projeto_registration.registration_api.models.User;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record UserDto(
        UUID id,
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String phone,
        @NotBlank
        @Pattern(
                regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",
                message = "CPF deve estar no formato xxx.xxx.xxx-xx"
        )
        String cpf
) {
    public UserDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getCpf()
        );
    }
}