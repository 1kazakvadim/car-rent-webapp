package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarClassServiceTest {

  @MockBean
  private CarClassService carClassService;

  private List<CarClass> carClasses;
  private CarClass carClass;

  @BeforeEach
  void init() {
    carClasses = new ArrayList<>();
    carClass = new CarClass();
    carClass.setName("name");
    carClasses.add(carClass);
  }

  @Test
  void findCarClassByName() {
    when(carClassService.findByName(carClass.getName())).thenReturn(carClass);
    assertThat(carClassService.findByName(carClass.getName())).isEqualTo(carClass);
  }

  @Test
  void getAllCarClasses() {
    when(carClassService.getAll()).thenReturn(carClasses);
    assertThat(carClassService.getAll()).isEqualTo(carClasses);
    assertEquals(1, carClasses.size());
  }

}
