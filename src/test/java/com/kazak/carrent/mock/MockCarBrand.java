package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarBrand;
import java.util.ArrayList;
import java.util.List;

public class MockCarBrand {

  private MockCarBrand() {
  }

  public static CarBrand getMockCarBrand() {
    return CarBrand.builder()
        .id(1)
        .name("AUDI")
        .build();
  }

  public static List<CarBrand> getMockCarBrands() {
    List<CarBrand> carBrands = new ArrayList<>();
    CarBrand carBrandOne = CarBrand.builder()
        .id(1)
        .name("BUGATTI")
        .build();
    CarBrand carBrandTwo = CarBrand.builder()
        .id(2)
        .name("CHEVROLET")
        .build();
    carBrands.add(carBrandOne);
    carBrands.add(carBrandTwo);
    return carBrands;
  }

}
