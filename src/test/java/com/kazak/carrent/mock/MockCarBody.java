package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarBody;
import java.util.ArrayList;
import java.util.List;

public class MockCarBody {

  private MockCarBody() {
  }

  public static CarBody getMockCarBody() {
    return CarBody.builder()
        .id(1)
        .name("SEDAN")
        .build();
  }

  public static List<CarBody> getMockCarBodies() {
    List<CarBody> carBodies = new ArrayList<>();
    CarBody carBodyOne = CarBody.builder()
        .id(1)
        .name("SEDAN")
        .build();
    CarBody carBodyTwo = CarBody.builder()
        .id(2)
        .name("PICKUP")
        .build();
    carBodies.add(carBodyOne);
    carBodies.add(carBodyTwo);
    return carBodies;
  }

}
