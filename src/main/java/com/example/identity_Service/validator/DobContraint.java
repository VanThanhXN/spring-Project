package com.example.identity_Service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;


@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {Dobvalidater.class}
)
public @interface DobContraint {
    String message() default "Invalid Date Format";

    int min() ;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
