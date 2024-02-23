package org.example.dunnoappapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private Boolean notEmpty;
    private Integer min;
    private Integer max;
    private String messageNotEmpty;
    private String messageWrongData;
    private String messageLength;

    @Override
    public void initialize(Username field) {
        notEmpty = field.notEmpty();
        min = field.min();
        max = field.max();
        messageNotEmpty = field.messageNotEmpty();
        messageWrongData = field.message();
        messageLength = field.messageLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Username:" + messageNotEmpty).addConstraintViolation();
            return false;
        }
        if ((min > 0 || max < Integer.MAX_VALUE) && (value.length() < min || value.length() > max)) {
            context.buildConstraintViolationWithTemplate("Username:" + messageLength).addConstraintViolation();
            return false;
        }
        if (!value.matches("^[A-Za-z0-9]+$")){
            context.buildConstraintViolationWithTemplate("Username:" + messageWrongData).addConstraintViolation();
            return false;
        }
        return true;
    }
}
