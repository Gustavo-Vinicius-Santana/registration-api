package br.com.projeto_registration.registration_api.repository;

import br.com.projeto_registration.registration_api.models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    Optional<UserAuth> findByEmailIgnoreCase(String email);
}
