package br.com.projeto_registration.registration_api.repository;

import br.com.projeto_registration.registration_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailContaining(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}