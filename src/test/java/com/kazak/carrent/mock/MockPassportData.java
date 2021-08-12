package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.PassportData;
import java.time.LocalDate;

public class MockPassportData {

  private MockPassportData() {
  }

  public static PassportData getMockPassportData() {
    return PassportData.builder()
        .id(1)
        .firstName("firstName")
        .lastName("lastName")
        .middleName("middleName")
        .sex("male")
        .dateOfBirth(LocalDate.of(2020, 1, 1))
        .passportNumber("AB1111111")
        .identificationNumber("3060PB41111111")
        .dateOfIssue(LocalDate.of(2020, 1, 1))
        .dateOfExpiry(LocalDate.of(2030, 1, 1))
        .placeOfBirth("REPUBLIC OF BELARUS")
        .authority("MINISTRY OF INTERNAL AFFAIRS")
        .nationality("REPUBLIC OF BELARUS")
        .registration("BREST")
        .build();
  }

}
