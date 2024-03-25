package org.example.dunnoappapi.validations;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = UsernameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {
    String message() default "Wrong data of string field";

    String messageNotEmpty() default "Field can't be empty";

    String messageLength() default "Wrong length of field";

    boolean notNull() default true;

    boolean notEmpty() default false;

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
