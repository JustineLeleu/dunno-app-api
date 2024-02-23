package org.example.dunnoappapi.modules.dtos;

import lombok.Data;
import org.example.dunnoappapi.validations.*;

@Data
@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Passwords do not match"
)
public class UserDto {

    @Username(notEmpty = true, min = 3, max = 26,
            messageNotEmpty = "Enter your name",
            messageLength = "Minimum 3 characters required",
            message = "Wrong or invalid username")
    private String username;

    @Email(notEmpty = true,
            messageNotEmpty = "Enter your email",
            message = "Wrong or invalid e-mail")
    private String email;

    @Password(notEmpty = true, min = 6, max = 26, strongPassword = true,
            messageNotEmpty = "Enter a password",
            messageLength = "Minimum 6 characters required",
            message = "Must contain one uppercase and one number")
    private String password;

    private String confirmPassword;
}
