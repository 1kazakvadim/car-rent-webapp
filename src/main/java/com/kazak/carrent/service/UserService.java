package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.User;

public interface UserService {

  User findByUsername(String username);

}
