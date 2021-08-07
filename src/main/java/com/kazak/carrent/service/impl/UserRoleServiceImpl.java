package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.UserRole;
import com.kazak.carrent.repository.UserRoleRepository;
import com.kazak.carrent.service.UserRoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRoleRepository userRoleRepository;

  public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
    this.userRoleRepository = userRoleRepository;
  }

  @Override
  public UserRole findByName(String name) {
    return userRoleRepository.findByName(name);
  }

  @Override
  public List<UserRole> getAll() {
    return userRoleRepository.findAll();
  }

}
