package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

  private final UserService userService;

  public ProfileController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile")
  public String getProfileMenu() {
    return "profile";
  }

  @GetMapping("/profile/information")
  public String getInformationPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    User user = userService.findByUsername(currentUser.getUsername());
    model.addAttribute("user", user);
    return "information";
  }

  @GetMapping("/profile/user")
  public String getAllUser(Model model) {
    List<User> users = userService.getAll();
    model.addAttribute("users", users);
    return "user";
  }

  @GetMapping("/profile/user/{id}/passport")
  public String getPassport(@PathVariable Integer id, Model model) {
    User user = userService.findById(id);
    PassportData passportData = user.getPassportData();
    model.addAttribute("passportData", passportData);
    return "passport";
  }


}
