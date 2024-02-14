package org.example.dunnoappapi.repositories;

import org.example.dunnoappapi.modules.entities.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Short> {
}
