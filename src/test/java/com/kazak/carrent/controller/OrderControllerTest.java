package com.kazak.carrent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockCarOrder;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.service.CarOrderService;
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
class OrderControllerTest {

  private final CarOrder carOrder = MockCarOrder.getMockCarOrder();

  @MockBean
  private CarOrderService carOrderService;

  @Autowired
  private MockMvc mockMvc;


  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldGetOrderCancellationReason() throws Exception {
    when(carOrderService.findById(carOrder.getId())).thenReturn(carOrder);
    assertThat(carOrderService.findById(carOrder.getId())).isEqualTo(carOrder);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders/{orderId}/reason", carOrder.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrder", carOrderService.findById(carOrder.getId())))
        .andExpect(MockMvcResultMatchers.view().name("order/order_reason"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldGetOrderDetail() throws Exception {
    when(carOrderService.findById(carOrder.getId())).thenReturn(carOrder);
    assertThat(carOrderService.findById(carOrder.getId())).isEqualTo(carOrder);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders/{orderId}/detail", carOrder.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrder", carOrderService.findById(carOrder.getId())))
        .andExpect(MockMvcResultMatchers.view().name("order/order_detail"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetOrderCancellation() throws Exception {
    when(carOrderService.findById(carOrder.getId())).thenReturn(carOrder);
    assertThat(carOrderService.findById(carOrder.getId())).isEqualTo(carOrder);
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.get("/profile/orders/{orderId}/cancellation", carOrder.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrder", carOrderService.findById(carOrder.getId())))
        .andExpect(MockMvcResultMatchers.view().name("order/order_cancellation"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetAddRepair() throws Exception {
    when(carOrderService.findById(carOrder.getId())).thenReturn(carOrder);
    assertThat(carOrderService.findById(carOrder.getId())).isEqualTo(carOrder);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders/{orderId}/repair", carOrder.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrder", carOrderService.findById(carOrder.getId())))
        .andExpect(MockMvcResultMatchers.view().name("order/order_repair"));
  }

}
