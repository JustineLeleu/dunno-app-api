package org.example.dunnoappapi.services;

import org.example.dunnoappapi.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

@Service
public class MembershipService {

    MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository){
        this.membershipRepository = membershipRepository;
    }
}
