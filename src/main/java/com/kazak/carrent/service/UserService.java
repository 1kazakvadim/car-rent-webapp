package com.kazak.carrent.service;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

  User findByUsername(String username);

  List<User> getAll();

  User findById(Integer id);

  boolean isUsernameExists(String username);

  boolean isUsernameExists(String username, Integer id);

  boolean isEmailExists(String email);

  boolean isEmailExists(String email, Integer id);

  boolean isPhoneNumberExists(String phoneNumber);

  boolean isPhoneNumberExists(String phoneNumber, Integer id);

  boolean changeUserPassword(Integer userId, String password, String passwordConfirm);

  boolean changeUserPasswordByUser(UserDetails currentUser, String passwordOld, String password,
      String passwordConfirm);

  void save(User user, PassportData passportData);

  void update(UserPostDto userPostDto, Integer userId);

}
