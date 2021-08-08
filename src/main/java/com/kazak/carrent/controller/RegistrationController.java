package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import javax.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public RegistrationController(UserService userService,
      PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/registration")
  public String getRegistrationPage(@ModelAttribute("user") User user,
      @ModelAttribute("passportData") PassportData passportData) {
    return "main/registration";
  }

  @PostMapping("/registration")
  public String registerUser(@Valid User user, BindingResult bindingResultUser,
      @Valid PassportData passportData, BindingResult bindingResultPassportData) {
    if (bindingResultUser.hasErrors() || bindingResultPassportData.hasErrors()) {
      return "main/registration";
    }
    user.setPassportData(passportData);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userService.save(user);
    return "main/login";
  }

}
