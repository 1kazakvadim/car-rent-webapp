package com.kazak.carrent.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockUser;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc()
class ProfileControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  private final User user = MockUser.getMockUser("usernameTest");


  @WithMockUser
  @Test
  void shouldReturnProfilePage() throws Exception {
    System.err.println(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/profile"));
  }

  @WithMockUser
  @Test
  void shouldReturnProfilePersonalInformationPage() throws Exception {
    when(userService.findByUsername(user.getUsername())).thenReturn(user);
    assertThat(userService.findByUsername(user.getUsername())).isEqualTo(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/information"))
        .andExpect(model().attribute("user", userService.findByUsername(user.getUsername())))
        .andExpect(model().attributeExists("user"))
        .andDo(MockMvcResultHandlers.print());
//        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_personal_information"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldReturnProfileCarsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_cars"));
  }

  @WithMockUser
  @Test
  void shouldReturnProfileOrdersPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/orders"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_orders"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldReturnProfileRepairsPage() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/repairs"))
        .andExpect(MockMvcResultMatchers.status().isOk())
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
        .andExpect(MockMvcResultMatchers.view().name("profile_nav/nav_users"));
  }

}
