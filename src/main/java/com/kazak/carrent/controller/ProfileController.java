package com.kazak.carrent.controller;

import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

  private final UserService userService;
  private final CarService carService;
  private final CarOrderService carOrderService;
  private final CarRepairService carRepairService;


  @GetMapping("/profile")
  public String getProfileMenu() {
    return "profile_nav/profile";
  }

  @GetMapping("/profile/information")
  public String getProfilePersonalInformation(@AuthenticationPrincipal UserDetails currentUser,
      Model model) {
    model.addAttribute("user", userService.findByUsername(currentUser.getUsername()));
    return "profile_nav/nav_personal_information";
  }

  @GetMapping("/profile/cars")
  public String getProfileCars(Model model) {
    model.addAttribute("cars", carService.getAll());
    return "profile_nav/nav_cars";
  }

  @GetMapping("/profile/orders")
  public String getProfileOrders(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    model.addAttribute("carOrders", carOrderService.getAll(currentUser));
    return "profile_nav/nav_orders";
  }

  @GetMapping("/profile/repairs")
  public String getProfileRepairs(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    model.addAttribute("carRepairs", carRepairService.getAll(currentUser));
    return "profile_nav/nav_repairs";
  }

  @GetMapping("/profile/settings")
  public String getProfileSettings(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    model.addAttribute("user", userService.findByUsername(currentUser.getUsername()));
    return "profile_nav/nav_settings";
  }

  @GetMapping("/profile/users")
  public String getProfileUsers(Model model) {
    model.addAttribute("users", userService.getAll());
    return "profile_nav/nav_users";
  }

}
