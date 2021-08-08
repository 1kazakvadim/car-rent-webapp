package com.kazak.carrent.annotation;

import com.kazak.carrent.validator.PassportNumberValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PassportNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniquePassportNumber {

  public String message() default "{validation.passportNumber.unique}";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};

}