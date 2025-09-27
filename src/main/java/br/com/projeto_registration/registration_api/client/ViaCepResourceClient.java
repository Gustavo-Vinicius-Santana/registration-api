package br.com.projeto_registration.registration_api.client;

import br.com.projeto_registration.registration_api.dto.ViaCepResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Component
public class ViaCepResourceClient {

    private final WebClient webClient;

    public ViaCepResourceClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }

    public ViaCepResponseDto getCep(String cep) {
        try {
            return webClient.get()
                    .uri("/{cep}/json", cep)
                    .retrieve()
                    .bodyToMono(ViaCepResponseDto.class)
                    .block(); // transforma reativo em sincrono
        } catch (WebClientResponseException e) {
            // Aqui você pode tratar erros HTTP (404, 500, etc)
            e.printStackTrace();
            return null;
        }
    }
}