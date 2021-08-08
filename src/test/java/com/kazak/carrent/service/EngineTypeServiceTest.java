package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kazak.carrent.model.entity.EngineType;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
  void findEngineTypeByName_True() {
    Mockito.when(engineTypeService.findByName(engineType.getName()))
        .thenReturn(engineType);
    assertThat(engineTypeService.findByName(engineType.getName()))
        .isEqualTo(engineType);
  }

  @Test
  void findEngineTypeByName_False() {
    Mockito.when(engineTypeService.findByName("name")).thenReturn(null);
    assertThat(engineTypeService.findByName("name")).isNull();
  }

  @Test
  void getAllEngineTypes_True() {
    Mockito.when(engineTypeService.getAll()).thenReturn(engineTypes);
    assertThat(engineTypeService.getAll()).isEqualTo(engineTypes);
  }

  @Test
  void getAllEngineTypes_False() {
    Mockito.when(engineTypeService.getAll()).thenReturn(null);
    assertThat(engineTypeService.getAll()).isNull();
  }

}
