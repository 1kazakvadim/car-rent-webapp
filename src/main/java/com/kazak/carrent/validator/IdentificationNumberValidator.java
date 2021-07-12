package com.kazak.carrent.validator;

import com.kazak.carrent.annotation.UniqueIdentificationNumber;
import com.kazak.carrent.service.PassportDataService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificationNumberValidator implements
    ConstraintValidator<UniqueIdentificationNumber, String> {

  private final PassportDataService passportDataService;

  public IdentificationNumberValidator(
      PassportDataService passportDataService) {
    this.passportDataService = passportDataService;
  }

  @Override
  public void initialize(UniqueIdentificationNumber constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !passportDataService.isIdentificationNumberExists(value);
  }

}
