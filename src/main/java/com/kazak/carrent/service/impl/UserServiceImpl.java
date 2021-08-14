package com.kazak.carrent.service.impl;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.mapper.UserMapper;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.repository.UserRoleRepository;
import com.kazak.carrent.service.UserService;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String USER_ROLE = "USER";
  private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{3,16}$";

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;


  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public User findById(Integer id) {
    return userRepository.getById(id);
  }

  @Override
  public boolean isUsernameExists(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean isUsernameExists(String username, Integer id) {
    return userRepository.existsByUsernameAndIdIsNot(username, id);
  }

  @Override
  public boolean isEmailExists(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean isEmailExists(String email, Integer id) {
    return userRepository.existsByEmailAndIdIsNot(email, id);
  }

  @Override
  public boolean isPhoneNumberExists(String phoneNumber) {
    return userRepository.existsByPhoneNumber(phoneNumber);
  }

  @Override
  public boolean isPhoneNumberExists(String phoneNumber, Integer id) {
    return userRepository.existsByPhoneNumberAndIdIsNot(phoneNumber, id);
  }

  @Override
  @Transactional
  public boolean changeUserPassword(Integer userId, String password, String passwordConfirm) {
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    if (!password.equals(passwordConfirm) || !matcher.find()) {
      return false;
    }
    User user = userRepository.getById(userId);
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
    return true;
  }

  @Override
  @Transactional
  public boolean changeUserPasswordByUser(UserDetails currentUser, String passwordOld,
      String password,
      String passwordConfirm) {
    User user = userRepository.findByUsername(currentUser.getUsername());
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    if (!password.equals(passwordConfirm) || !passwordEncoder
        .matches(passwordOld, user.getPassword()) || !matcher.find()) {
      return false;
    }
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
    return true;
  }

  @Override
  @Transactional
  public void save(User user, PassportData passportData) {
    user.setPassportData(passportData);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserRole(userRoleRepository.findByName(USER_ROLE));
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void update(UserPostDto userPostDto, Integer userId) {
    User user = userRepository.getById(userId);
    userMapper.updateUserFromDto(userPostDto, user);
    userRepository.save(user);
  }

}
