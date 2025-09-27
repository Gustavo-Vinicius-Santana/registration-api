package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.RegistrationRequestDTO;
import br.com.projeto_registration.registration_api.dto.RegistrationResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RegistrationService {
    Page<RegistrationResponseDTO> findAll(Pageable pagination);
    RegistrationResponseDTO findById(UUID id);
    RegistrationResponseDTO save(RegistrationRequestDTO registrationRequestDTO);
    RegistrationResponseDTO update(UUID id, RegistrationRequestDTO registrationRequestDTO);
    void delete(UUID id);
}
