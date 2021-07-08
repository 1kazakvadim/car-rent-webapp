package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.PassportData;

public interface PassportDataService {

  PassportData findById(Integer id);

  boolean isPassportNumberExists(String passportNumber);

  boolean isIdentificationNumberExists(String identificationNumber);

}
