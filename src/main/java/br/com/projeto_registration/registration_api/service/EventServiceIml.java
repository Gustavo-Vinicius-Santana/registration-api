package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.client.ViaCepResourceClient;
import br.com.projeto_registration.registration_api.dto.EventDto;
import br.com.projeto_registration.registration_api.dto.UserDto;
import br.com.projeto_registration.registration_api.dto.ViaCepResponseDto;
import br.com.projeto_registration.registration_api.models.Event;
import br.com.projeto_registration.registration_api.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceIml implements EventService {

    private final EventRepository eventRepository;
    private final ViaCepResourceClient viaCepClient;

    @Override
    public Page<EventDto> findAll(Pageable pagination) {
        return eventRepository.findAll(pagination).map(EventDto::new);
    }

    @Override
    public EventDto findById(UUID id) {
        return eventRepository.findById(id)
                .map(EventDto::new)
                .orElseThrow(() -> new EntityNotFoundException("id não encontrado."));
    }

    @Override
    public EventDto findByCep(String cep) {
        return eventRepository.findByCepContaining(cep)
                .map(EventDto::new)
                .orElseThrow(() -> new EntityNotFoundException("teste"));
    }

    @Override
    public EventDto save(EventDto eventDto) {
        ViaCepResponseDto endereco = viaCepClient.getCep(eventDto.cep());

        if (endereco == null || endereco.getErro() != null) {
            throw new RuntimeException("CEP inválido ou não encontrado: " + eventDto.cep());
        }

        var event = new Event();
        event.setName(eventDto.name());
        event.setDateEvent(eventDto.dateEvent());
        event.setCep(eventDto.cep());
        event.setState(endereco.getEstado());
        event.setCity(endereco.getLocalidade());

        return new EventDto(eventRepository.save(event));
    }

    @Override
    public EventDto update(UUID id, EventDto eventDto) {
        var event = eventRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada"));

        event.setCep(eventDto.cep());
        event.setDateEvent(eventDto.dateEvent());
        event.setCep(eventDto.cep());

        return new EventDto(eventRepository.save(event));
    }

    @Override
    public void deleteId(UUID id) {
        var event = eventRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("pessoa não encontrada"));

        eventRepository.deleteById(id);
    }
}
