package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

  private final UserService userService;

  public ProfileController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile")
  public String getProfilePage(Model model) {
    return "profile";
  }

  @GetMapping("/profile/user")
  public String getAllUser(Model model) {
    List<User> users = userService.getAll();
    model.addAttribute("users", users);
    return "user";
  }



}
