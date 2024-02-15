package org.example.dunnoappapi.controllers;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.example.dunnoappapi.exceptions.CreationFailedException;
import org.example.dunnoappapi.modules.dtos.UserDto;
import org.example.dunnoappapi.modules.entities.Membership;
import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.services.MembershipService;
import org.example.dunnoappapi.services.RoleService;
import org.example.dunnoappapi.services.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final MembershipService membershipService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, MembershipService membershipService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.membershipService = membershipService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable UUID id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity<Object> addUser(@Valid @RequestBody UserDto userDto) {
        User newUser = new User();

        try{
            if (userService.userExistByUsername(userDto.getUsername()) || userService.userExistByEmail(userDto.getEmail()))
                throw new CreationFailedException("error");
        }catch (CreationFailedException ex){
            List<String> messages = new ArrayList<>();
            if (userService.userExistByUsername(userDto.getUsername()))
                messages.add("Username already exist");
            if (userService.userExistByEmail(userDto.getEmail()))
                messages.add("Email already used");
            throw new CreationFailedException(messages);
        }

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        newUser.setRole(roleService.getRoleById((short) 1));

        userService.createUser(newUser);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT, params = {"membershipId"})
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @RequestParam Short membershipId){
        User user = userService.getUserById(id);

        Membership membership = membershipService.getMembershipById(membershipId);
        user.setRole(roleService.getRoleById((short) 2));
        user.setMembership(membership);

        Date start = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MONTH, membership.getDuration());
        Date end = cal.getTime();
        user.setSubscription_start(start);
        user.setSubscription_end(end);

        userService.createUser(user);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT, params = {})
    public ResponseEntity<Object> updateUser(@PathVariable UUID id){
        User user = userService.getUserById(id);

        user.setRole(roleService.getRoleById((short) 1));
        user.setMembership(null);
        user.setSubscription_start(null);
        user.setSubscription_end(null);

        userService.createUser(user);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }
}