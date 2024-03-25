package org.example.dunnoappapi.modules.dtos;

import lombok.Data;
import org.example.dunnoappapi.validations.Email;
import org.example.dunnoappapi.validations.Password;
import org.example.dunnoappapi.validations.Username;

@Data
public class UserUpdateDto {
    @Username(notNull = false, notEmpty = true, min = 3, max = 26,
            messageLength = "Minimum 3 characters required",
            message = "Wrong or invalid username")
    private String username;

    @Email(notNull = false, notEmpty = true,
            message = "Wrong or invalid e-mail")
    private String email;

    @Password(notNull = false, notEmpty = true, min = 6, max = 26, strongPassword = true,
            messageLength = "Minimum 6 characters required",
            message = "Must contain one uppercase and one number")
    private String password;
}
