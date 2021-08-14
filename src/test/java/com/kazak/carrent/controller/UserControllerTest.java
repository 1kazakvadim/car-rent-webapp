package com.kazak.carrent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.mock.MockUser;
import com.kazak.carrent.mock.MockUserRole;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserRole;
import com.kazak.carrent.service.PassportDataService;
import com.kazak.carrent.service.UserRoleService;
import com.kazak.carrent.service.UserService;
import java.util.List;
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
class UserControllerTest {

  private final User user = MockUser.getMockUser("username");
  private final List<UserRole> userRoles = MockUserRole.getMockUserRoles();

  @MockBean
  private UserService userService;

  @MockBean
  private PassportDataService passportDataService;

  @MockBean
  private UserRoleService userRoleService;

  @Autowired
  private MockMvc mockMvc;

  @WithMockUser(roles = {"ADMIN"})
  @Test
  void shouldGetOrderCancellation() throws Exception {
    when(userService.findById(user.getId())).thenReturn(user);
    assertThat(userService.findById(user.getId())).isEqualTo(user);
    when(userRoleService.getAll()).thenReturn(userRoles);
    assertThat(userRoleService.getAll()).isEqualTo(userRoles);
    assertEquals(2, userRoles.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/users/{userId}/edit", user.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attributeExists("userDto"))
        .andExpect(model().attribute("user", userService.findById(user.getId())))
        .andExpect(model().attribute("userRoles", userRoleService.getAll()))
        .andExpect(MockMvcResultMatchers.view().name("user/user_edit"));

  }

  @WithMockUser(roles = {"ADMIN"})
  @Test
  void shouldGetUserPassport() throws Exception {
    when(userService.findById(user.getId())).thenReturn(user);
    assertThat(userService.findById(user.getId())).isEqualTo(user);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/users/{userId}/passport", user.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            model().attribute("passportData", userService.findById(user.getId()).getPassportData()))
        .andExpect(MockMvcResultMatchers.view().name("user/user_passport"));
  }

  @WithMockUser(roles = {"ADMIN"})
  @Test
  void shouldGetPassportEdit() throws Exception {
    when(userService.findById(user.getId())).thenReturn(user);
    assertThat(userService.findById(user.getId())).isEqualTo(user);
    when(passportDataService.findById(user.getPassportData().getId()))
        .thenReturn(user.getPassportData());
    assertThat(passportDataService.findById(user.getPassportData().getId()))
        .isEqualTo(user.getPassportData());
    this.mockMvc
        .perform(MockMvcRequestBuilders
            .get("/profile/users/{userId}/passport/{passportId}/edit", user.getId(),
                user.getPassportData().getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            model().attribute("user", userService.findById(user.getId())))
        .andExpect(
            model().attribute("passportData",
                passportDataService.findById(user.getPassportData().getId())))
        .andExpect(MockMvcResultMatchers.view().name("user/passport_edit"));
  }

}
