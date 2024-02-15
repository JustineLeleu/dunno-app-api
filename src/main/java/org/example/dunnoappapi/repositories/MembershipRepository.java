package org.example.dunnoappapi.repositories;

import org.example.dunnoappapi.modules.entities.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Short> {
}
