package com.kazak.carrent.annotation;

import com.kazak.carrent.validator.EmailValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface UniqueEmail {

  public String message() default "such email exists";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};

}
