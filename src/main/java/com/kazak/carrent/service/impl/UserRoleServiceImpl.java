package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.UserRole;
import com.kazak.carrent.repository.UserRoleRepository;
import com.kazak.carrent.service.UserRoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRoleRepository userRoleRepository;


  @Override
  public UserRole findByName(String name) {
    return userRoleRepository.findByName(name);
  }

  @Override
  public List<UserRole> getAll() {
    return userRoleRepository.findAll();
  }

}
