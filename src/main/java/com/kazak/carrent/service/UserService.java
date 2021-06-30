package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.User;
import java.util.List;

public interface UserService {

  User findByUsername(String username);

  List<User> getAll();

}
