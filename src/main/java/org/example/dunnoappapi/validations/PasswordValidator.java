package org.example.dunnoappapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private Boolean notNull;
    private Boolean notEmpty;
    private Boolean strongPassword;
    private Integer min;
    private Integer max;
    private String messageNotEmpty;
    private String messageWrongData;
    private String messageLength;

    @Override
    public void initialize(Password field) {
        notEmpty = field.notEmpty();
        notNull = field.notNull();
        strongPassword = field.strongPassword();
        min = field.min();
        max = field.max();
        messageNotEmpty = field.messageNotEmpty();
        messageWrongData = field.message();
        messageLength = field.messageLength();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (notNull && value == null){
            context.buildConstraintViolationWithTemplate("Password:" + messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (notEmpty && value.isEmpty()) {
            context.buildConstraintViolationWithTemplate("Password:" + messageNotEmpty).addConstraintViolation();
            return false;
        }
        if (value != null && !value.isEmpty() && (min > 0 || max < Integer.MAX_VALUE) && (value.length() < min || value.length() > max)) {
            context.buildConstraintViolationWithTemplate("Password:" + messageLength).addConstraintViolation();
            return false;
        }
        if (value != null && !value.isEmpty() && strongPassword && !value.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,30}$")){
            context.buildConstraintViolationWithTemplate("Password:" + messageWrongData).addConstraintViolation();
            return false;
        }
        return true;
    }
}
