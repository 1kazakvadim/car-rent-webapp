package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
import java.util.ArrayList;
import java.util.List;

public class MockCar {

  private MockCar() {
  }

  public static Car getMockCar() {
    return Car.builder()
        .id(1)
        .carBrand(CarBrand.builder().id(1).name("AUDI").build())
        .model("A8")
        .carClass(CarClass.builder().id(1).name("LUX").build())
        .carTransmission(CarTransmission.builder().id(1).name("AT").build())
        .engineType(EngineType.builder().id(1).name("PETROL").build())
        .engineVolume(5D)
        .numberOfSeats(5)
        .fuelConsumption(5D)
        .rentalCost(200D)
        .imageName("a8.jpg")
        .build();
  }

  public static List<Car> getMockCars() {
    List<Car> cars = new ArrayList<>();
    Car carOne = Car.builder()
        .id(1)
        .carBrand(CarBrand.builder().id(1).name("BMW").build())
        .model("M5")
        .carClass(CarClass.builder().id(1).name("LUX").build())
        .carTransmission(CarTransmission.builder().id(1).name("AT").build())
        .engineType(EngineType.builder().id(1).name("PETROL").build())
        .engineVolume(5D)
        .numberOfSeats(5)
        .fuelConsumption(5D)
        .rentalCost(200D)
        .imageName("m5.jpg")
        .build();
    Car carTwo = Car.builder()
        .id(2)
        .carBrand(CarBrand.builder().id(1).name("BMW").build())
        .model("M3")
        .carClass(CarClass.builder().id(1).name("LUX").build())
        .carTransmission(CarTransmission.builder().id(1).name("AT").build())
        .engineType(EngineType.builder().id(1).name("PETROL").build())
        .engineVolume(5D)
        .numberOfSeats(5)
        .fuelConsumption(5D)
        .rentalCost(200D)
        .imageName("m3.jpg")
        .build();
    cars.add(carOne);
    cars.add(carTwo);
    return cars;
  }

}
