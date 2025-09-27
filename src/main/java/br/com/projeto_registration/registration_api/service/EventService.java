package br.com.projeto_registration.registration_api.service;

import br.com.projeto_registration.registration_api.dto.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {
    Page<EventDto> findAll(Pageable pagination);
    EventDto findById(UUID id);
    EventDto findByCep(String cep);
    EventDto save(EventDto eventDto);
    EventDto update(UUID id, EventDto eventDto);
    void deleteId(UUID id);
}
