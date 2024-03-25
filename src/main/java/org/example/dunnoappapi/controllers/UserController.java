package org.example.dunnoappapi.controllers;

import jakarta.validation.Valid;
import org.example.dunnoappapi.exceptions.CreationFailedException;
import org.example.dunnoappapi.modules.dtos.UserDto;
import org.example.dunnoappapi.modules.dtos.UserUpdateDto;
import org.example.dunnoappapi.modules.entities.Membership;
import org.example.dunnoappapi.modules.entities.User;
import org.example.dunnoappapi.services.MembershipService;
import org.example.dunnoappapi.services.RoleService;
import org.example.dunnoappapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

        if (userService.userExistByUsername(userDto.getUsername()) || userService.userExistByEmail(userDto.getEmail())){
            throw new CreationFailedException("Username or email already taken");
        }

        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail().toLowerCase());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        newUser.setRole(roleService.getRoleById((short) 1));

        userService.createUser(newUser);

        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    // Update user: add premium and calculate subscription end
    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateDto userDto){
        User user = userService.getUserById(id);

        if (userDto.getUsername() != null) user.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) user.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null) user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userService.createUser(user);
        return new ResponseEntity<>("User updated", HttpStatus.OK);
    }

    // Update user: add premium and calculate subscription end
    @RequestMapping(value = "/api/user/subscription/{id}", method = RequestMethod.PUT, params = {"membershipId"})
    public ResponseEntity<Object> updateUserSubscription(@PathVariable UUID id, @RequestParam Short membershipId){
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
        return new ResponseEntity<>("User updated: add premium", HttpStatus.OK);
    }

    // Update the user: remove premium
    @RequestMapping(value = "/api/user/subscription/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUserSubscription(@PathVariable UUID id){
        User user = userService.getUserById(id);

        user.setRole(roleService.getRoleById((short) 1));
        user.setMembership(null);
        user.setSubscription_start(null);
        user.setSubscription_end(null);

        userService.createUser(user);
        return new ResponseEntity<>("User updated: remove premium", HttpStatus.OK);
    }

    // Update the user: already premium and add months to subscription
    // ! problem with membership type if change ?
    @RequestMapping(value = "/api/user/subscription/{id}", method = RequestMethod.PUT, params = {"months"})
    public ResponseEntity<Object> updateUserSubscription(@PathVariable UUID id, @RequestParam int months){
        User user = userService.getUserById(id);

        Calendar cal = Calendar.getInstance();
        cal.setTime(user.getSubscription_end());
        cal.add(Calendar.MONTH, months);
        Date end = cal.getTime();
        user.setSubscription_end(end);

        userService.createUser(user);
        return new ResponseEntity<>("User updated: add months", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id){
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }
}
