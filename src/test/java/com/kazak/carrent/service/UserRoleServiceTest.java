package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kazak.carrent.model.entity.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserRoleServiceTest {

  @MockBean
  private UserRoleService userRoleService;

  private List<UserRole> userRoles;
  private UserRole userRole;

  @BeforeEach
  void init() {
    userRoles = new ArrayList<>();
    userRole = new UserRole();
    userRole.setName("name");
    userRoles.add(userRole);
  }

  @Test
  void findUserRoleByName() {
    Mockito.when(userRoleService.findByName(userRole.getName()))
        .thenReturn(userRole);
    assertThat(userRoleService.findByName(userRole.getName()))
        .isEqualTo(userRole);
  }

  @Test
  void getAllUserRoles() {
    Mockito.when(userRoleService.getAll()).thenReturn(userRoles);
    assertThat(userRoleService.getAll()).isEqualTo(userRoles);
    assertEquals(1, userRoles.size());
  }

}
