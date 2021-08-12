package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.mock.MockCarTransmission;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarTransmissionServiceTest {

  @MockBean
  private CarTransmissionService carTransmissionService;

  private final List<CarTransmission> carTransmissions = MockCarTransmission
      .getMockCarTransmissions();
  private final CarTransmission carTransmission = MockCarTransmission.getMockCarTransmission();

  @Test
  void findCarTransmissionByName() {
    when(carTransmissionService.findByName(carTransmission.getName()))
        .thenReturn(carTransmission);
    assertThat(carTransmissionService.findByName(carTransmission.getName()))
        .isEqualTo(carTransmission);
  }

  @Test
  void getAllCarTransmissions() {
    when(carTransmissionService.getAll()).thenReturn(carTransmissions);
    assertThat(carTransmissionService.getAll()).isEqualTo(carTransmissions);
    assertEquals(2, carTransmissions.size());
  }

}
