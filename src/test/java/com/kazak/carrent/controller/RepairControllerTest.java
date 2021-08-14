package com.kazak.carrent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockCarRepair;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.service.CarRepairService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class RepairControllerTest {

  private final CarRepair carRepair = MockCarRepair.getMockCarRepair();

  @MockBean
  private CarRepairService carRepairService;

  @Autowired
  private MockMvc mockMvc;

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnRegistrationPage() throws Exception {
    when(carRepairService.findById(carRepair.getId())).thenReturn(carRepair);
    assertThat(carRepairService.findById(carRepair.getId())).isEqualTo(carRepair);
    this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/profile/repairs/{repairId}/damage-information", carRepair.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carRepair", carRepairService.findById(carRepair.getId())))
        .andExpect(MockMvcResultMatchers.view().name("repair/repair_damage_information"));
  }

}
