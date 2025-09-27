package br.com.projeto_registration.registration_api.dto;

import br.com.projeto_registration.registration_api.models.Event;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventDto(
        UUID id,
        String name,
        LocalDateTime dateEvent,
        String cep,
        String state,
        String city
) {
    public EventDto(Event event){
        this(
            event.getId(),
            event.getName(),
            event.getDateEvent(),
            event.getCep(),
            event.getState(),
            event.getCity()
        );
    }

}
