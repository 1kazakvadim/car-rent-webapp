package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  private final UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String registrationPage(@ModelAttribute("user") User user,
      @ModelAttribute("passportData") PassportData passportData) {
    return "registration";
  }

  @PostMapping("/registration")
  public String registrationUser(@Valid @ModelAttribute("user") User user,
      BindingResult bindingResultUser,
      @Valid @ModelAttribute("passportData") PassportData passportData,
      BindingResult bindingResultPassportData) {
    if (bindingResultUser.hasErrors() || bindingResultPassportData.hasErrors()) {
      return "registration";
    }
    userService.save(user, passportData);
    return "login";
  }


}
