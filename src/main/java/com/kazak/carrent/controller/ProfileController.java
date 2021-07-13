package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.CarOrderService;
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
  private final CarOrderService carOrderService;

  public ProfileController(UserService userService,
      CarOrderService carOrderService) {
    this.userService = userService;
    this.carOrderService = carOrderService;
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

  @GetMapping("/profile/order")
  public String getAllOrder(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    List<CarOrder> carOrders = carOrderService.getAll(currentUser);
    model.addAttribute("carOrders", carOrders);
    return "order";
  }

  @GetMapping("/profile/order/{id}/cancellation")
  public String getOrderCancellation(@PathVariable Integer id, Model model){
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "cancellation";
  }

  @GetMapping("/profile/order/{id}/detail")
  public String getOrderDetail(@PathVariable Integer id, Model model) {
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "detail";
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
