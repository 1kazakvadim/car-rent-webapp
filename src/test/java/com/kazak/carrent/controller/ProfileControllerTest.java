package com.kazak.carrent.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockUser;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ProfileControllerTest {

  @MockBean
  private UserService userService;

  @MockBean
  private CarOrderService carOrderService;

  @MockBean
  private CarRepairService carRepairService;

  @MockBean
  private CarService carService;

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private UserDetails currentUser;

  private final User user = MockUser.getMockUser("username");


  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfilePersonalInformationPage() throws Exception {
    when(userService.findByUsername(any())).thenReturn(user);
    assertThat(userService.findByUsername(user.getUsername())).isEqualTo(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/information"))
        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(model().attribute("user", user))
        .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_personal_information"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldReturnProfileCarsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("cars", carService.getAll()))
        .andExpect(model().attributeExists("cars"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_cars"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfileOrdersPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrders", carOrderService.getAll(currentUser)))
        .andExpect(model().attributeExists("carOrders"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_orders"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfileRepairsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/repairs"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carRepairs", carRepairService.getAll(currentUser)))
        .andExpect(model().attributeExists("carRepairs"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_repairs"));
  }

  @WithMockUser
  @Test
  void shouldReturnProfileSettingsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/settings"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_settings"));
  }

  @WithMockUser(roles = {"ADMIN"})
  @Test
  void shouldReturnProfileUsersPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("users", userService.getAll()))
        .andExpect(model().attributeExists("users"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_users"));
  }

}
