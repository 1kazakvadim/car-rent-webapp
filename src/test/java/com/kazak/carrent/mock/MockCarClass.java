package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarClass;
import java.util.ArrayList;
import java.util.List;

public class MockCarClass {

  private MockCarClass() {
  }

  public static CarClass getMockCarClass() {
    return CarClass.builder()
        .id(1)
        .name("AVERAGE")
        .build();
  }

  public static List<CarClass> getMockCarClasses() {
    List<CarClass> carClasses = new ArrayList<>();
    CarClass carClassOne = CarClass.builder()
        .id(1)
        .name("FAMILY")
        .build();
    CarClass carClassTwo = CarClass.builder()
        .id(2)
        .name("LUX")
        .build();
    carClasses.add(carClassOne);
    carClasses.add(carClassTwo);
    return carClasses;
  }

}
