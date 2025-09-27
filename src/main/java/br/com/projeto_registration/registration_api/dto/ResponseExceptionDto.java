package br.com.projeto_registration.registration_api.dto;

import java.time.LocalDateTime;

public record ResponseExceptionDto(
        LocalDateTime dateTime,
        Integer status,
        String error,
        String message
) {

}
