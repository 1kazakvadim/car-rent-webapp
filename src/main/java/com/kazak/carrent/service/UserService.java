package com.kazak.carrent.service;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.User;
import java.util.List;

public interface UserService {

  User findByUsername(String username);

  List<User> getAll();

  User findById(Integer id);

  boolean isUsernameExists(String username);

  boolean isUsernameExistsExceptUsernameWithId(String username, Integer id);

  boolean isEmailExists(String email);

  boolean isEmailExistsExceptEmailWithId(String email, Integer id);

  boolean isPhoneNumberExists(String phoneNumber);

  boolean isPhoneNumberExistsExceptPhoneNumberWithId(String phoneNumber, Integer id);

  boolean checkForValidOldPassword(User user, String oldPassword);

  void changeUserPassword(User user, String password);

  void save(User user);

  void update(UserPostDto userPostDto);

}
