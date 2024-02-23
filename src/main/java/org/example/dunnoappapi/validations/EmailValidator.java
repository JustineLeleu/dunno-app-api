package org.example.dunnoappapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {
    private Boolean notEmpty;
    private String messageNotEmpty;
    private String messageWrongData;

    @Override
    public void initialize(Email field) {
        notEmpty = field.notEmpty();
        messageNotEmpty = field.messageNotEmpty();
        messageWrongData = field.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Email:" + messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (!value.matches("(?i)^[A-Za-z0-9+_.-]+@(gmail\\.com|yahoo\\.com|hotmail\\.com|outlook\\.com|aol\\.com|icloud\\.com|protonmail\\.com|mail\\.com)$")){
            context.buildConstraintViolationWithTemplate("Email:" + messageWrongData).addConstraintViolation();
            return false;
        }
        return true;
    }
}
