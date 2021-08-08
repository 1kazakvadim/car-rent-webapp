package com.kazak.carrent.service.impl;

import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.mapper.UserMapper;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.repository.UserRoleRepository;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      UserRoleRepository userRoleRepository,
      UserMapper userMapper, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userRoleRepository = userRoleRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

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
  public boolean isUsernameExistsExceptUsernameWithId(String username, Integer id) {
    return userRepository.existsByUsernameAndIdIsNot(username, id);
  }

  @Override
  public boolean isEmailExists(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public boolean isEmailExistsExceptEmailWithId(String email, Integer id) {
    return userRepository.existsByEmailAndIdIsNot(email, id);
  }

  @Override
  public boolean isPhoneNumberExists(String phoneNumber) {
    return userRepository.existsByPhoneNumber(phoneNumber);
  }

  @Override
  public boolean isPhoneNumberExistsExceptPhoneNumberWithId(String phoneNumber, Integer id) {
    return userRepository.existsByPhoneNumberAndIdIsNot(phoneNumber, id);
  }

  @Override
  public boolean checkForValidOldPassword(User user, String oldPassword) {
    return passwordEncoder.matches(oldPassword, user.getPassword());
  }

  @Override
  @Transactional
  public void changeUserPassword(Integer userId, String password) {
    User user = userRepository.getById(userId);
    user.setPassword(passwordEncoder.encode(password));
    userRepository.save(user);
  }

  @Override
  @Transactional
  public void save(User user, PassportData passportData) {
    user.setPassportData(passportData);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setUserRole(userRoleRepository.findByName("USER"));
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
