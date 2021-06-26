package com.kazak.carrent.service.impl;

import com.kazak.carrent.service.DatabaseUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsServiceImpl implements DatabaseUserDetailsService,
    UserDetailsService {

  private final UserAccountService userAccountService;

  @Autowired
  public DatabaseUserDetailsServiceImpl(
      UserAccountService userAccountService) {
    this.userAccountService = userAccountService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserAccount userAccount = userAccountService.findByUsername(username);
    if (userAccount == null) {
      throw new UsernameNotFoundException(username);
    }
    return new MyUserPrincipal(userAccount);
  }
}
