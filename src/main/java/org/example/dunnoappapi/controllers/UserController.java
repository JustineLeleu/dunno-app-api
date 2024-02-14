package org.example.dunnoappapi.controllers;

import jakarta.validation.Valid;
import org.example.dunnoappapi.modules.dtos.UserDto;
import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.services.RoleService;
import org.example.dunnoappapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto userDto){
        User newUser = userDto.toUser();
        newUser.setRole(roleService.getRoleById((short) 1));

        userService.createUser(newUser);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }
}
