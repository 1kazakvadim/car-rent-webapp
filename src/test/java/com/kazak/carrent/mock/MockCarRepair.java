package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockCarRepair {

  private MockCarRepair() {
  }

  public static CarRepair getMockCarRepair() {
    return CarRepair.builder()
        .id(1)
        .damageInformation("damageInformation")
        .repairCost(1000D)
        .build();
  }

  public static List<CarRepair> getMockCarRepairs() {
    List<CarRepair> carRepairs = new ArrayList<>();
    CarRepair carRepairOne = CarRepair.builder()
        .id(1)
        .carOrder(CarOrder.builder()
            .id(1)
            .user(User.builder()
                .id(1)
                .username("username")
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
                .userRole(new UserRole(1, "USER"))
                .status("ACTIVE")
                .createdAt(LocalDate.now()).build()).build())
        .damageInformation("damageInformation")
        .repairCost(1000D)
        .build();
    CarRepair carRepairTwo = CarRepair.builder()
        .id(2)
        .damageInformation("damageInformation")
        .carOrder(CarOrder.builder()
            .id(2)
            .user(User.builder()
                .id(1)
                .username("username")
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
                .userRole(new UserRole(1, "USER"))
                .status("ACTIVE")
                .createdAt(LocalDate.now()).build()).build())
        .repairCost(1000D)
        .build();
    carRepairs.add(carRepairOne);
    carRepairs.add(carRepairTwo);
    return carRepairs;
  }

}
