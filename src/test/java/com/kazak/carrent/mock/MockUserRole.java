package com.kazak.carrent.mock;

import com.kazak.carrent.model.entity.UserRole;
import java.util.ArrayList;
import java.util.List;

public class MockUserRole {

  private MockUserRole() {
  }

  public static UserRole getMockUserRole() {
    return UserRole.builder()
        .id(1)
        .name("ADMIN")
        .build();
  }

  public static List<UserRole> getMockUserRoles() {
    List<UserRole> userRoles = new ArrayList<>();
    UserRole userRoleOne = UserRole.builder()
        .id(1)
        .name("MANAGER")
        .build();
    UserRole userRoleTwo = UserRole.builder()
        .id(2)
        .name("USER")
        .build();
    userRoles.add(userRoleOne);
    userRoles.add(userRoleTwo);
    return userRoles;
  }

}
