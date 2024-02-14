package org.example.dunnoappapi.services;

import org.example.dunnoappapi.modules.entities.Role;
import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Short id){
        return roleRepository.findById(id).orElseThrow();
    }
}
