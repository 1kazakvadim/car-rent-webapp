package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.UserRole;
import java.util.List;

public interface UserRoleService {

  UserRole findByName(String name);

  List<UserRole> getAll();

}
