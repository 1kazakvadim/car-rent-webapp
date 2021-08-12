package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.CarRepair;
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
        .damageInformation("damageInformation")
        .repairCost(1000D)
        .build();
    CarRepair carRepairTwo = CarRepair.builder()
        .id(2)
        .damageInformation("damageInformation")
        .repairCost(1000D)
        .build();
    carRepairs.add(carRepairOne);
    carRepairs.add(carRepairTwo);
    return carRepairs;
  }

}
