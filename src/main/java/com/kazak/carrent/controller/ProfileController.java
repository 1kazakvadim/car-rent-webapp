package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

  private final UserService userService;
  private final CarService carService;
  private final CarOrderService carOrderService;
  private final CarRepairService carRepairService;

  public ProfileController(UserService userService,
      CarService carService, CarOrderService carOrderService,
      CarRepairService carRepairService) {
    this.userService = userService;
    this.carService = carService;
    this.carOrderService = carOrderService;
    this.carRepairService = carRepairService;
  }

  @GetMapping("/profile")
  public String getProfileMenu() {
    return "profile_nav/profile";
  }

  @GetMapping("/profile/information")
  public String getProfilePersonalInformation(@AuthenticationPrincipal UserDetails currentUser,
      Model model) {
    User user = userService.findByUsername(currentUser.getUsername());
    model.addAttribute("user", user);
    return "profile_nav/nav_personal_information";
  }

  @GetMapping("/profile/car")
  public String getProfileCar(Model model) {
    List<Car> cars = carService.getAll();
    model.addAttribute("cars", cars);
    return "profile_nav/nav_car";
  }

  @GetMapping("/profile/order")
  public String getProfileOrder(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    List<CarOrder> carOrders = carOrderService.getAll(currentUser);
    model.addAttribute("carOrders", carOrders);
    return "profile_nav/nav_order";
  }

  @GetMapping("/profile/repair")
  public String getProfileRepair(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    List<CarRepair> carRepairs = carRepairService.getAll(currentUser);
    model.addAttribute("carRepairs", carRepairs);
    return "profile_nav/nav_repair";
  }

  @GetMapping("/profile/setting")
  public String getSettingPage(@AuthenticationPrincipal UserDetails currentUser, Model model) {
    User user = userService.findByUsername(currentUser.getUsername());
    model.addAttribute("user", user);
    return "profile_nav/nav_setting";
  }

  @GetMapping("/profile/user")
  public String getProfileUser(Model model) {
    List<User> users = userService.getAll();
    model.addAttribute("users", users);
    return "profile_nav/nav_user";
  }

}
