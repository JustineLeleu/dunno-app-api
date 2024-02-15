package org.example.dunnoappapi.services;

import org.example.dunnoappapi.modules.entities.Membership;
import org.example.dunnoappapi.repositories.MembershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MembershipService {

    MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository){
        this.membershipRepository = membershipRepository;
    }

    public List<Membership> getAllMemberships(){
        return (List<Membership>) membershipRepository.findAll();
    }

    public Membership getMembershipById(Short id){
        return membershipRepository.findById(id).orElseThrow();
    }
}
