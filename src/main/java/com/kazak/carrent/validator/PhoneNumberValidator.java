package com.kazak.carrent.validator;

import com.kazak.carrent.annotation.UniquePhoneNumber;
import com.kazak.carrent.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<UniquePhoneNumber, String> {

  private final UserService userService;

  public PhoneNumberValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !userService.isPhoneNumberExists(value);
  }

}
