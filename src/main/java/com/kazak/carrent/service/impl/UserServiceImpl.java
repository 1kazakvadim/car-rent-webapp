package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

}
