package br.com.projeto_registration.registration_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaCepResponseDto {
    private String localidade;
    private String estado;
    private String erro;
}
