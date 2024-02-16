package org.example.dunnoappapi.services;

import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User getUserById(UUID id){
        return userRepository.findById(id);
    }

    public boolean userExistByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean userExistByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
