package br.com.projeto_registration.registration_api.repository;

import br.com.projeto_registration.registration_api.models.Event;
import br.com.projeto_registration.registration_api.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
}
