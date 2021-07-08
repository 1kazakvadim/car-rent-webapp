package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import java.util.List;

public interface UserService {

  User findByUsername(String username);

  List<User> getAll();

  User findById(Integer id);

  User save(User user, PassportData passportData);

}
