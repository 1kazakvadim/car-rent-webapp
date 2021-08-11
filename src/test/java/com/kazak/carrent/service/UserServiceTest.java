package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {

  private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{3,16}$";

  @MockBean
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private PasswordEncoder passwordEncoder;

  private List<User> users;
  private User user;

  @BeforeEach
  void init() {
    users = new ArrayList<>();
    user = User.builder()
        .id(1)
        .email("email")
        .phoneNumber("1111")
        .build();
    users.add(user);
  }

  @Test
  void findUserByUsername() {
    when(userService.findByUsername(user.getUsername())).thenReturn(user);
    assertThat(userService.findByUsername(user.getUsername())).isEqualTo(user);
  }

  @Test
  void getAllUsers() {
    when(userService.getAll()).thenReturn(users);
    assertThat(userService.getAll()).isEqualTo(users);
    assertEquals(1, users.size());
  }

  @Test
  void isUsernameExists_True() {
    when(userService.isUsernameExists(user.getUsername())).thenReturn(true);
    assertThat(userService.isUsernameExists(user.getUsername())).isTrue();
  }

  @Test
  void isUsernameExists_False() {
    when(userService.isUsernameExists("0")).thenReturn(false);
    assertThat(userService.isUsernameExists("0")).isFalse();
  }

  @Test
  void isEmailExists_True() {
    when(userService.isEmailExists(user.getEmail())).thenReturn(true);
    assertThat(
        userService.isEmailExists(user.getEmail())).isTrue();
  }

  @Test
  void isEmailExists_False() {
    when(userService.isEmailExists("0")).thenReturn(false);
    assertThat(
        userService.isEmailExists("0")).isFalse();
  }

  @Test
  void isPhoneNumberExists_True() {
    when(userService.isPhoneNumberExists(user.getEmail())).thenReturn(true);
    assertThat(
        userService.isPhoneNumberExists(user.getEmail())).isTrue();
  }

  @Test
  void isPhoneNumberExists_False() {
    when(userService.isPhoneNumberExists("0")).thenReturn(false);
    assertThat(
        userService.isPhoneNumberExists("0")).isFalse();
  }

  @Test
  void changeUserPassword() {
    String password = new String("paSSword1$");
    String passwordConfirm = new String("paSSword1$");
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    assertThat(password.equals(passwordConfirm) && matcher.find()).isTrue();
    when(userService.findById(user.getId())).thenReturn(user);
    assertThat(userService.findById(user.getId())).isEqualTo(user);
    String encodedPassword = passwordEncoder.encode(password);
    user.setPassword(encodedPassword);
    userRepository.save(user);
    verify(userRepository, times(1)).save(user);
  }

  @Test
  void saveUser() {
    userService.save(user, new PassportData());
    verify(userService, times(1)).save(user, new PassportData());
  }

  @Test
  void updateUser() {
    UserPostDto userPostDto = new UserPostDto();
    userService.update(userPostDto, 1);
    verify(userService, times(1)).update(userPostDto, 1);
  }

}
