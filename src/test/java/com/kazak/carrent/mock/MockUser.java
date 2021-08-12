package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MockUser {

  private MockUser() {

  }

  public static User getMockUser(String username) {
    return User.builder()
        .id(1)
        .username(username)
        .password("pa55W0rd$")
        .email("email")
        .phoneNumber("+375291111111")
        .passportData(PassportData.builder()
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
            .build())
        .userRole(new UserRole(1, "ADMIN"))
        .status("ACTIVE")
        .createdAt(LocalDate.now())
        .build();
  }

  public static List<User> getMockUsers() {
    List<User> users = new ArrayList<>();
    User userOne = User.builder()
        .id(1)
        .username("userOne")
        .password("pa55W0rd$")
        .email("email")
        .phoneNumber("+375291111111")
        .passportData(PassportData.builder()
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
            .build())
        .userRole(new UserRole(1, "ADMIN"))
        .status("ACTIVE")
        .createdAt(LocalDate.now())
        .build();
    User userTwo = User.builder()
        .id(1)
        .username("userTwo")
        .password("pa55W0rd$")
        .email("email")
        .phoneNumber("+375292222222")
        .passportData(PassportData.builder()
            .id(1)
            .firstName("firstName")
            .lastName("lastName")
            .middleName("middleName")
            .sex("male")
            .dateOfBirth(LocalDate.of(2020, 2, 2))
            .passportNumber("AB2222222")
            .identificationNumber("3060PB42222222")
            .dateOfIssue(LocalDate.of(2020, 2, 2))
            .dateOfExpiry(LocalDate.of(2030, 2, 2))
            .placeOfBirth("REPUBLIC OF BELARUS")
            .authority("MINISTRY OF INTERNAL AFFAIRS")
            .nationality("REPUBLIC OF BELARUS")
            .registration("BREST")
            .build())
        .userRole(new UserRole(2, "MANAGER"))
        .status("ACTIVE")
        .createdAt(LocalDate.now())
        .build();
    users.add(userOne);
    users.add(userTwo);
    return users;
  }

}
