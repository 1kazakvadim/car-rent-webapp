package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserRole;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MockCarOrder {

  private MockCarOrder() {
  }

  public static CarOrder getMockCarOrder() {
    return CarOrder.builder()
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
                .build()).build())
        .car(Car.builder()
            .id(1)
            .carBrand(CarBrand.builder().id(1).name("BMW").build())
            .model("M5")
            .carBody(CarBody.builder().id(1).name("LIMOUSINE").build())
            .carClass(CarClass.builder().id(1).name("LUX").build())
            .carTransmission(CarTransmission.builder().id(1).name("AT").build())
            .engineType(EngineType.builder().id(1).name("PETROL").build())
            .engineVolume(5D)
            .numberOfSeats(5)
            .fuelConsumption(5D)
            .rentalCost(200D)
            .imageName("m5.jpg")
            .build())
        .dateOfIssue(LocalDate.now())
        .dateOfReturn(LocalDate.now().plusDays(1))
        .isCancellation(false)
        .reasonOfCancellation("")
        .totalCost(1000D)
        .build();
  }

  public static List<CarOrder> getMockCarOrders() {
    List<CarOrder> carOrders = new ArrayList<>();
    CarOrder carOrderOne = CarOrder.builder()
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
            .createdAt(LocalDate.now()).build())
        .car(Car.builder()
            .id(1)
            .carBrand(CarBrand.builder().id(1).name("BMW").build())
            .model("M5")
            .carBody(CarBody.builder().id(1).name("LIMOUSINE").build())
            .carClass(CarClass.builder().id(1).name("LUX").build())
            .carTransmission(CarTransmission.builder().id(1).name("AT").build())
            .engineType(EngineType.builder().id(1).name("PETROL").build())
            .engineVolume(5D)
            .numberOfSeats(5)
            .fuelConsumption(5D)
            .rentalCost(200D)
            .imageName("m5.jpg")
            .build())
        .dateOfIssue(LocalDate.now())
        .dateOfReturn(LocalDate.now().plusDays(1))
        .isCancellation(false)
        .reasonOfCancellation("")
        .totalCost(1000D)
        .build();
    CarOrder carOrderTwo = CarOrder.builder()
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
            .createdAt(LocalDate.now()).build())
        .car(Car.builder()
            .id(1)
            .carBrand(CarBrand.builder().id(1).name("BMW").build())
            .model("M5")
            .carBody(CarBody.builder().id(1).name("LIMOUSINE").build())
            .carClass(CarClass.builder().id(1).name("LUX").build())
            .carTransmission(CarTransmission.builder().id(1).name("AT").build())
            .engineType(EngineType.builder().id(1).name("PETROL").build())
            .engineVolume(5D)
            .numberOfSeats(5)
            .fuelConsumption(5D)
            .rentalCost(200D)
            .imageName("m5.jpg")
            .build())
        .dateOfIssue(LocalDate.now().plusDays(4))
        .dateOfReturn(LocalDate.now().plusDays(5))
        .isCancellation(false)
        .reasonOfCancellation("")
        .totalCost(2000D)
        .build();
    carOrders.add(carOrderOne);
    carOrders.add(carOrderTwo);
    return carOrders;
  }


}
