package br.com.projeto_registration.registration_api.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RegistrationRequestDTO(
        @NotNull(message = "User ID é obrigatório")
        UUID userId,

        @NotNull(message = "Event ID é obrigatório")
        UUID eventId
) {
    // O status pode ser definido no service como PENDING por default
}