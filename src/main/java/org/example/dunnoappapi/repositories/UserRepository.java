package org.example.dunnoappapi.repositories;

import org.example.dunnoappapi.modules.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Short> {
}
