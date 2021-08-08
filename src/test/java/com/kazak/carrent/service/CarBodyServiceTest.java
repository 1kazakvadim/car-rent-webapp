package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.kazak.carrent.model.entity.CarBody;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class CarBodyServiceTest {

  @MockBean
  private CarBodyService carBodyService;

  private List<CarBody> carBodies;
  private CarBody carBody;

  @BeforeEach
  void init() {
    carBodies = new ArrayList<>();
    carBody = new CarBody();
    carBody.setName("name");
    carBodies.add(carBody);
  }

  @Test
  void findCarBodyByName_True() {
    Mockito.when(carBodyService.findByName(carBody.getName())).thenReturn(carBody);
    assertThat(carBodyService.findByName(carBody.getName())).isEqualTo(carBody);
  }

  @Test
  void findCarBodyByName_False() {
    Mockito.when(carBodyService.findByName("name")).thenReturn(null);
    assertThat(carBodyService.findByName("name")).isNull();
  }

  @Test
  void getAllCarBodies_True() {
    Mockito.when(carBodyService.getAll()).thenReturn(carBodies);
    assertThat(carBodyService.getAll()).isEqualTo(carBodies);
  }

  @Test
  void getAllCarBodies_False() {
    Mockito.when(carBodyService.getAll()).thenReturn(null);
    assertThat(carBodyService.getAll()).isNull();
  }

}
