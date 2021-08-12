package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.mock.MockCarClass;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarClassServiceTest {

  @MockBean
  private CarClassService carClassService;

  private final List<CarClass> carClasses = MockCarClass.getMockCarClasses();
  private final CarClass carClass = MockCarClass.getMockCarClass();

  @Test
  void findCarClassByName() {
    when(carClassService.findByName(carClass.getName())).thenReturn(carClass);
    assertThat(carClassService.findByName(carClass.getName())).isEqualTo(carClass);
  }

  @Test
  void getAllCarClasses() {
    when(carClassService.getAll()).thenReturn(carClasses);
    assertThat(carClassService.getAll()).isEqualTo(carClasses);
    assertEquals(2, carClasses.size());
  }

}
