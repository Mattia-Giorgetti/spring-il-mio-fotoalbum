package org.project.java.springfotoalbum.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ValidImageFile {
    String message() default "Invalid image file: file must be not empty and of type png or jpg";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
