package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.UserRole;
import com.kazak.carrent.mock.MockUserRole;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserRoleServiceTest {

  @MockBean
  private UserRoleService userRoleService;

  private final List<UserRole> userRoles = MockUserRole.getMockUserRoles();
  private final UserRole userRole = MockUserRole.getMockUserRole();

  @Test
  void findUserRoleByName() {
    when(userRoleService.findByName(userRole.getName()))
        .thenReturn(userRole);
    assertThat(userRoleService.findByName(userRole.getName()))
        .isEqualTo(userRole);
  }

  @Test
  void getAllUserRoles() {
    when(userRoleService.getAll()).thenReturn(userRoles);
    assertThat(userRoleService.getAll()).isEqualTo(userRoles);
    assertEquals(2, userRoles.size());
  }

}
