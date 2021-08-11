package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.EngineType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EngineTypeServiceTest {

  @MockBean
  private EngineTypeService engineTypeService;

  private List<EngineType> engineTypes;
  private EngineType engineType;

  @BeforeEach
  void init() {
    engineTypes = new ArrayList<>();
    engineType = new EngineType();
    engineType.setName("name");
    engineTypes.add(engineType);
  }

  @Test
  void findEngineTypeByName() {
    when(engineTypeService.findByName(engineType.getName()))
        .thenReturn(engineType);
    assertThat(engineTypeService.findByName(engineType.getName()))
        .isEqualTo(engineType);
  }

  @Test
  void getAllEngineTypes() {
    when(engineTypeService.getAll()).thenReturn(engineTypes);
    assertThat(engineTypeService.getAll()).isEqualTo(engineTypes);
    assertEquals(1, engineTypes.size());
  }

}
