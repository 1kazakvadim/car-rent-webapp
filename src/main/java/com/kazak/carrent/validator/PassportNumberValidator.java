package com.kazak.carrent.validator;

import com.kazak.carrent.annotation.UniquePassportNumber;
import com.kazak.carrent.service.PassportDataService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PassportNumberValidator implements ConstraintValidator<UniquePassportNumber, String> {

  private final PassportDataService passportDataService;

  public PassportNumberValidator(PassportDataService passportDataService) {
    this.passportDataService = passportDataService;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !passportDataService.isPassportNumberExists(value);
  }

}
