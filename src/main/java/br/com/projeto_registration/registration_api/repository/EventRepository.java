package br.com.projeto_registration.registration_api.repository;

import br.com.projeto_registration.registration_api.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    Optional<Event> findByCepContaining(String cep);
}
