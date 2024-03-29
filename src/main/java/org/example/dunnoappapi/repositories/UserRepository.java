package org.example.dunnoappapi.repositories;

import io.micrometer.observation.ObservationFilter;
import org.example.dunnoappapi.modules.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, Short> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
