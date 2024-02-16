package org.example.dunnoappapi.security;

import org.example.dunnoappapi.modules.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;

    public AuthService(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public Map<String, Object> createLoginInfo(Authentication authentication){
        // Create user infos
        SecurityUser principal = (SecurityUser) authentication.getPrincipal();
        User user = principal.getUser();

        // Create a JWT
        String token = jwtProvider.createToken(authentication);

        Map<String, Object> loginResultMap = new HashMap<>();
        loginResultMap.put("userInfos", user);
        loginResultMap.put("token", token);

        return loginResultMap;
    }
}
