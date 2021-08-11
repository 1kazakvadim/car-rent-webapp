package com.kazak.carrent.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

  @MockBean
  private UserService userService;

  @MockBean
  private CarService carService;

  @MockBean
  private CarOrderService carOrderService;

  @MockBean
  private CarRepairService carRepairService;

  @MockBean
  private UserDetails currentUser;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Autowired
  private MockMvc mockMvc;

  private User user;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    user = User.builder()
        .id(1)
        .username("username1")
        .email("email")
        .phoneNumber("1111")
        .build();
  }

  @WithMockUser("spring")
  @Test
  void shouldReturnProfilePage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/profile"));
  }

  @WithMockUser("spring")
  @Test
  void shouldReturnProfilePersonalInformationPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/information"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("user"))
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_personal_information"));
  }

  @Test
  void shouldReturnProfileCarsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_cars"));
  }

  @Test
  void shouldReturnProfileOrdersPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_orders"));
  }

  @Test
  void shouldReturnProfileRepairsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/repairs"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_repairs"));
  }

  @WithMockUser("spring")
  @Test
  void shouldReturnProfileSettingsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/settings"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_settings"));
  }

  @Test
  void shouldReturnProfileUsersPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/users"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_users"));
  }

}
