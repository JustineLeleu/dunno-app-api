package org.example.dunnoappapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Validator;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchingValidator implements ConstraintValidator<PasswordMatching, Object> {
    private String password;
    private String confirmPassword;
    private String message;
    private final Validator validator;

    public PasswordMatchingValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void initialize(PasswordMatching field) {
        password = field.password();
        confirmPassword = field.confirmPassword();
        message = field.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object passwordValue = new BeanWrapperImpl(value).getPropertyValue(password);
        Object confirmPasswordValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);

        context.disableDefaultConstraintViolation();

        if (!validator.validateValue(value.getClass(), password, passwordValue).isEmpty()) {
            return true; // skip validation if password is not correct
        }

        if (!passwordValue.equals(confirmPasswordValue)) {
            context.buildConstraintViolationWithTemplate("ConfirmPassword:" + message).addConstraintViolation();
            return false;
        }
        return true;
    }
}