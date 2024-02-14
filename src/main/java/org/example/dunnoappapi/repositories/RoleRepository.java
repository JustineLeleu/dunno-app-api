package org.example.dunnoappapi.repositories;

import org.example.dunnoappapi.modules.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {
}
