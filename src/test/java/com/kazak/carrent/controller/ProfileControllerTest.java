package com.kazak.carrent.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockCar;
import com.kazak.carrent.mock.MockCarOrder;
import com.kazak.carrent.mock.MockCarRepair;
import com.kazak.carrent.mock.MockUser;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserPrincipal;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import com.kazak.carrent.service.impl.DatabaseUserDetailsServiceImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
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

  private final User user = MockUser.getMockUser("username");
  private final List<Car> cars = MockCar.getMockCars();
  private final List<CarOrder> orders = MockCarOrder.getMockCarOrders();
  private final List<CarRepair> repairs = MockCarRepair.getMockCarRepairs();
  private final List<User> users = MockUser.getMockUsers();
  private final UserPrincipal userPrincipal = new UserPrincipal(user);

  @MockBean
  private UserService userService;

  @MockBean
  private CarOrderService carOrderService;

  @MockBean
  private CarRepairService carRepairService;

  @MockBean
  private CarService carService;

  @MockBean
  private DatabaseUserDetailsServiceImpl databaseUserDetailsService;

  @MockBean
  private UserDetails currentUser;

  @Autowired
  private MockMvc mockMvc;


  @WithMockUser(username = "username", password = "pa55W0rd$", roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfilePersonalInformationPage() throws Exception {
    when(currentUser.getUsername()).thenReturn("username");
    assertThat(currentUser.getUsername()).isEqualTo("username");
    when(databaseUserDetailsService.loadUserByUsername("username")).thenReturn(userPrincipal);
    assertThat(databaseUserDetailsService.loadUserByUsername("username")).isEqualTo(userPrincipal);
    when(userService.findByUsername(currentUser.getUsername())).thenReturn(user);
    assertThat(userService.findByUsername(currentUser.getUsername())).isEqualTo(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/information"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("user", userService.findByUsername(currentUser.getUsername())))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_personal_information"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldReturnProfileCarsPage() throws Exception {
    when(carService.getAll()).thenReturn(cars);
    assertThat(carService.getAll()).isEqualTo(cars);
    assertEquals(2, cars.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("cars"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_cars"));
  }

  @WithMockUser(username = "username", password = "pa55W0rd$", roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfileOrdersPage() throws Exception {
    when(carOrderService.getAll(any())).thenReturn(orders);
    assertThat(carOrderService.getAll(any())).isEqualTo(orders);
    assertEquals(2, orders.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carOrders", carOrderService.getAll(any())))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_orders"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfileRepairsPage() throws Exception {
    when(carRepairService.getAll(any())).thenReturn(repairs);
    assertThat(carRepairService.getAll(any())).isEqualTo(repairs);
    assertEquals(2, repairs.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/repairs"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carRepairs", carRepairService.getAll(any())))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_repairs"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER", "USER"})
  @Test
  void shouldReturnProfileSettingsPage() throws Exception {
    when(currentUser.getUsername()).thenReturn("username");
    assertThat(currentUser.getUsername()).isEqualTo("username");
    when(databaseUserDetailsService.loadUserByUsername("username")).thenReturn(userPrincipal);
    assertThat(databaseUserDetailsService.loadUserByUsername("username")).isEqualTo(userPrincipal);
    when(userService.findByUsername(currentUser.getUsername())).thenReturn(user);
    assertThat(userService.findByUsername(currentUser.getUsername())).isEqualTo(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/settings"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_settings"));
  }

  @WithMockUser(roles = {"ADMIN"})
  @Test
  void shouldReturnProfileUsersPage() throws Exception {
    when(userService.getAll()).thenReturn(users);
    assertThat(userService.getAll()).isEqualTo(users);
    assertEquals(2, users.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("users"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_users"));
  }

}
