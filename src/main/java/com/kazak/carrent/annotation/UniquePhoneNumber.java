package com.kazak.carrent.annotation;

import com.kazak.carrent.validator.PhoneNumberValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniquePhoneNumber {

  public String message() default "such phone number exists";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};

}