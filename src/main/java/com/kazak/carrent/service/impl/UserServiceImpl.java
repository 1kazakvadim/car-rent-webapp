package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.repository.UserRoleRepository;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserRoleRepository userRoleRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      UserRoleRepository userRoleRepository) {
    this.userRepository = userRepository;

    this.userRoleRepository = userRoleRepository;
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
  @Transactional
  public User save(User user, PassportData passportData) {
    passportData.setUser(user);
    user.setPassportData(passportData);
    user.setUserRole(userRoleRepository.findByName("USER"));
    return userRepository.save(user);
  }

}
