package com.kazak.carrent.service;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.User;
import java.util.List;

public interface UserService {

  User findByUsername(String username);

  List<User> getAll();

  User findById(Integer id);

  boolean isUsernameExists(String username);

  boolean isEmailExists(String email);

  boolean isPhoneNumberExists(String phoneNumber);

  boolean checkForValidOldPassword(User user, String oldPassword);

  void changeUserPassword(User user, String password);

  void save(User user);

  void update(UserPostDto userPostDto);

}
