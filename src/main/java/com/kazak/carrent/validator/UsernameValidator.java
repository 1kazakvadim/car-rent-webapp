package com.kazak.carrent.validator;

import com.kazak.carrent.annotation.UniqueUsername;
import com.kazak.carrent.service.UserService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  private final UserService userService;

  public UsernameValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return value != null && !userService.isUsernameExists(value);
  }

}
