package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.mock.MockCarBody;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class CarBodyServiceTest {

  @MockBean
  private CarBodyService carBodyService;

  private final List<CarBody> carBodies = MockCarBody.getMockCarBodies();
  private final CarBody carBody = MockCarBody.getMockCarBody();

  @Test
  void findCarBodyByName() {
    when(carBodyService.findByName(carBody.getName())).thenReturn(carBody);
    assertThat(carBodyService.findByName(carBody.getName())).isEqualTo(carBody);
  }

  @Test
  void getAllCarBodies() {
    when(carBodyService.getAll()).thenReturn(carBodies);
    assertThat(carBodyService.getAll()).isEqualTo(carBodies);
    assertEquals(2, carBodies.size());
  }

}
