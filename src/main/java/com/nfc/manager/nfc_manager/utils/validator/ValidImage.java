package com.nfc.manager.nfc_manager.utils.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageTypeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImage {
    String message() default "Invalid image file. Only JPEG and PNG files are accepted.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}