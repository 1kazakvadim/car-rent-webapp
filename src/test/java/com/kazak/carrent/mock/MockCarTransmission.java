package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarTransmission;
import java.util.ArrayList;
import java.util.List;

public class MockCarTransmission {

  private MockCarTransmission() {
  }

  public static CarTransmission getMockCarTransmission() {
    return CarTransmission.builder()
        .id(1)
        .name("AT")
        .build();
  }

  public static List<CarTransmission> getMockCarTransmissions() {
    List<CarTransmission> carTransmissions = new ArrayList<>();
    CarTransmission carTransmissionOne = CarTransmission.builder()
        .id(1)
        .name("AT")
        .build();
    CarTransmission carTransmissionTwo = CarTransmission.builder()
        .id(2)
        .name("MT")
        .build();
    carTransmissions.add(carTransmissionOne);
    carTransmissions.add(carTransmissionTwo);
    return carTransmissions;
  }

}
