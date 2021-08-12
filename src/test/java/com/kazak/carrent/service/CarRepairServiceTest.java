package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.mock.MockCarRepair;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class CarRepairServiceTest {

  @MockBean
  private CarRepairService carRepairService;

  @MockBean
  private UserDetails currentUser;

  private final List<CarRepair> carRepairs = MockCarRepair.getMockCarRepairs();
  private final CarRepair carRepair = MockCarRepair.getMockCarRepair();

  @Test
  void findCarRepairById() {
    when(carRepairService.findById(carRepair.getId())).thenReturn(carRepair);
    assertThat(carRepairService.findById(carRepair.getId())).isEqualTo(carRepair);
  }

  @Test
  void getAllCarRepairs() {
    when(carRepairService.getAll()).thenReturn(carRepairs);
    assertThat(carRepairService.getAll()).isEqualTo(carRepairs);
    assertEquals(2, carRepairs.size());
  }

  @Test
  void getAllCarRepairsByUsername() {
    when(currentUser.getUsername()).thenReturn("username");
    when(carRepairService.getAll(currentUser)).thenReturn(carRepairs);
    assertThat(currentUser.getUsername()).isEqualTo("username");
    assertThat(carRepairService.getAll(currentUser)).isEqualTo(carRepairs);
    assertEquals(2, carRepairs.size());
  }

  @Test
  void saveCarRepair() {
    carRepairService.save(1, "damageInformation", 1000D);
    verify(carRepairService, times(1)).save(1, "damageInformation", 1000D);
  }

}
