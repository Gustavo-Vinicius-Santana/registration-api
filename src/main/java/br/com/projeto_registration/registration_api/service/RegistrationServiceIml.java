package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.RegistrationRequestDTO;
import br.com.projeto_registration.registration_api.dto.RegistrationResponseDTO;
import br.com.projeto_registration.registration_api.models.Event;
import br.com.projeto_registration.registration_api.models.Registration;
import br.com.projeto_registration.registration_api.models.User;
import br.com.projeto_registration.registration_api.repository.EventRepository;
import br.com.projeto_registration.registration_api.repository.RegistrationRepository;
import br.com.projeto_registration.registration_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceIml implements RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Override
    public Page<RegistrationResponseDTO> findAll(Pageable pagination) {
        return registrationRepository.findAll(pagination)
                .map(RegistrationResponseDTO::new);
    }

    @Override
    public RegistrationResponseDTO findById(UUID id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada: " + id));
        return new RegistrationResponseDTO(registration);
    }

    @Override
    public RegistrationResponseDTO save(RegistrationRequestDTO registrationRequestDTO) {
        // Buscar usuário
        User user = userRepository.findById(registrationRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + registrationRequestDTO.userId()));

        // Buscar evento
        Event event = eventRepository.findById(registrationRequestDTO.eventId())
                .orElseThrow(() -> new RuntimeException("Evento não encontrado: " + registrationRequestDTO.eventId()));

        // Criar nova inscrição
        Registration registration = Registration.builder()
                .user(user)
                .event(event)
                .status(Registration.RegistrationStatus.PENDING)
                .build();

        registrationRepository.save(registration);

        return new RegistrationResponseDTO(registration);
    }

    @Override
    public RegistrationResponseDTO update(UUID id, RegistrationRequestDTO registrationRequestDTO) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada: " + id));

        // Atualizar usuário, se necessário
        if (registrationRequestDTO.userId() != null) {
            User user = userRepository.findById(registrationRequestDTO.userId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + registrationRequestDTO.userId()));
            registration.setUser(user);
        }

        // Atualizar evento, se necessário
        if (registrationRequestDTO.eventId() != null) {
            Event event = eventRepository.findById(registrationRequestDTO.eventId())
                    .orElseThrow(() -> new RuntimeException("Evento não encontrado: " + registrationRequestDTO.eventId()));
            registration.setEvent(event);
        }

        registrationRepository.save(registration);
        return new RegistrationResponseDTO(registration);
    }

    @Override
    public void delete(UUID id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscrição não encontrada: " + id));
        registrationRepository.delete(registration);
    }
}