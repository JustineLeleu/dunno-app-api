package org.example.dunnoappapi.modules.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.dunnoappapi.modules.entities.User;

@Data
public class UserDto {

    @NotBlank(message = "Invalid username: Empty username")
    @NotNull(message = "Invalid username: Username is NULL")
    @Size(min = 3, max = 30, message = "Invalid username: Must be of 3 - 30 characters")
    private String username;

    @NotBlank(message = "Invalid email: Empty email")
    @NotNull(message = "Invalid email: email is NULL")
    @Email(message = "Invalid email", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotBlank(message = "Invalid password: Empty password")
    @NotNull(message = "Invalid password: Password is NULL")
    @Size(min = 6, max = 30, message = "Invalid password: Must be of 3 - 30 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,30}$",
            message = "Invalid password, your password must contain at least one uppercase letter, one lowercase letter and one number")
    private String password;
}
