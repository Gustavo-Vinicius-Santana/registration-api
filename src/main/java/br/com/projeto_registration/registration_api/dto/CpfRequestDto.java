package br.com.projeto_registration.registration_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CpfRequestDto {
    @NotBlank
    private String cpf;
}
