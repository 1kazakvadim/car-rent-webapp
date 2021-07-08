package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserPrincipal;
import com.kazak.carrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Autowired
  public DatabaseUserDetailsServiceImpl(
      UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    }
    return new UserPrincipal(user);
  }

}
