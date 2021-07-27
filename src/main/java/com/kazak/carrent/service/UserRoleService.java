package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.UserRole;

public interface UserRoleService {

  UserRole findByName(String name);

}
