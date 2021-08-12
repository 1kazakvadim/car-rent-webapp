package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserPrincipal;
import com.kazak.carrent.mock.MockUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DatabaseUserDetailsServiceTest {

  @MockBean
  private UserService userService;

  private final User user = MockUser.getMockUser("username");

  @Test
  void loadUserByUsername_True() {
    when(userService.findByUsername("username")).thenReturn(user);
    assertThat(userService.findByUsername("username")).isEqualTo(user);
    assertThat(new UserPrincipal(user)).isNotNull();
  }

}
