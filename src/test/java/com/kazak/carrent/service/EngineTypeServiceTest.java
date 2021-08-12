package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.EngineType;
import com.kazak.carrent.mock.MockEngineType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EngineTypeServiceTest {

  @MockBean
  private EngineTypeService engineTypeService;

  private final List<EngineType> engineTypes = MockEngineType.getMockEngineTypes();
  private final EngineType engineType = MockEngineType.getMockEngineType();

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
    assertEquals(2, engineTypes.size());
  }

}
