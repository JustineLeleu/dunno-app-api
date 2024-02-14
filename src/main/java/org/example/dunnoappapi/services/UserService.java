package org.example.dunnoappapi.services;

import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
