package br.com.projeto_registration.registration_api.dto;

import br.com.projeto_registration.registration_api.models.Registration;
import java.util.UUID;

public record RegistrationResponseDTO(
        UUID id,
        UUID userId,
        UUID eventId,
        String status
) {

    public RegistrationResponseDTO(Registration registration) {
        this(
                registration.getId(),
                registration.getUser().getId(),
                registration.getEvent().getId(),
                registration.getStatus().name()
        );
    }
}