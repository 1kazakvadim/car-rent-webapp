package com.kazak.carrent.validator;

import com.kazak.carrent.annotation.UniqueEmail;
import com.kazak.carrent.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UserService userService;

  public EmailValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value != null && !userService.isEmailExists(value);
  }

}
