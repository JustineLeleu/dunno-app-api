package org.example.dunnoappapi.validations;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "Wrong data of string field";

    String messageNotEmpty() default "Field can't be empty";

    boolean notNull() default true;

    boolean notEmpty() default false;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
