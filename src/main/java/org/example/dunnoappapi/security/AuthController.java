package org.example.dunnoappapi.security;

import jakarta.validation.Valid;
import org.example.dunnoappapi.controllers.UserController;
import org.example.dunnoappapi.modules.dtos.LoginDto;
import org.example.dunnoappapi.modules.dtos.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    UserController userController;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService, UserController userController) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.userController = userController;
    }

    // Post method for the authentication
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto loginDto){

        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

        return new ResponseEntity<>(authService.createLoginInfo(authenticationResponse), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserDto userDto){

        userController.addUser(userDto);

        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

        return new ResponseEntity<>(authService.createLoginInfo(authenticationResponse), HttpStatus.OK);
    }
}
