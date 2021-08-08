package com.kazak.carrent.service;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.model.entity.PassportData;

public interface PassportDataService {

  PassportData findById(Integer id);

  boolean isPassportNumberExists(String passportNumber);

  boolean isPassportNumberExistsExceptPassportNumberWithId(String passportNumber, Integer id);

  boolean isIdentificationNumberExists(String identificationNumber);

  boolean isIdentificationNumberExistsExceptIdentificationNumberWithId(String identificationNumber, Integer id);

  void update(PassportDataPostDto passportDataPostDto, Integer passportId);

}
