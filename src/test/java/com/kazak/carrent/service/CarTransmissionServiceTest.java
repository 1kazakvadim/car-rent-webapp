package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.kazak.carrent.model.entity.CarTransmission;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarTransmissionServiceTest {

  @MockBean
  private CarTransmissionService carTransmissionService;

  private List<CarTransmission> carTransmissions;
  private CarTransmission carTransmission;

  @BeforeEach
  void init() {
    carTransmissions = new ArrayList<>();
    carTransmission = new CarTransmission();
    carTransmission.setName("name");
    carTransmissions.add(carTransmission);
  }

  @Test
  void findCarTransmissionByName_True() {
    Mockito.when(carTransmissionService.findByName(carTransmission.getName()))
        .thenReturn(carTransmission);
    assertThat(carTransmissionService.findByName(carTransmission.getName()))
        .isEqualTo(carTransmission);
  }

  @Test
  void findCarTransmissionByName_False() {
    Mockito.when(carTransmissionService.findByName("name")).thenReturn(null);
    assertThat(carTransmissionService.findByName("name")).isNull();
  }

  @Test
  void getAllCarTransmissions_True() {
    Mockito.when(carTransmissionService.getAll()).thenReturn(carTransmissions);
    assertThat(carTransmissionService.getAll()).isEqualTo(carTransmissions);
  }

  @Test
  void getAllCarTransmissions_False() {
    Mockito.when(carTransmissionService.getAll()).thenReturn(null);
    assertThat(carTransmissionService.getAll()).isNull();
  }

}
