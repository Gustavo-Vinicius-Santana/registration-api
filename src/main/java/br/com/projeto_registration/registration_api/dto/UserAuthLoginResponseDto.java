package br.com.projeto_registration.registration_api.dto;

public record UserAuthLoginResponseDto(
        UserAuthResponseDto user,
        String token
) {

}
