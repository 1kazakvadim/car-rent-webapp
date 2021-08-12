package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.EngineType;
import java.util.ArrayList;
import java.util.List;

public class MockEngineType {

  private MockEngineType() {
  }

  public static EngineType getMockEngineType() {
    return EngineType.builder()
        .id(1)
        .name("PETROL")
        .build();
  }

  public static List<EngineType> getMockEngineTypes() {
    List<EngineType> engineTypes = new ArrayList<>();
    EngineType engineTypeOne = EngineType.builder()
        .id(1)
        .name("PETROL")
        .build();
    EngineType engineTypeTwo = EngineType.builder()
        .id(2)
        .name("DIESEL")
        .build();
    engineTypes.add(engineTypeOne);
    engineTypes.add(engineTypeTwo);
    return engineTypes;
  }

}
