package org.example.dunnoappapi.modules.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank(message = "Username:Enter your username")
    private String username;

    @NotBlank(message = "Password:Enter your password")
    private String password;
}
