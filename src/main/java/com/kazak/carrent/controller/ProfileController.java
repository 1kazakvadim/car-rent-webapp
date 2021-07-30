package com.kazak.carrent.controller;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.PassportDataService;
import com.kazak.carrent.service.UserRoleService;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {

  private final UserService userService;
  private final CarService carService;
  private final CarOrderService carOrderService;
  private final UserRoleService userRoleService;
  private final PassportDataService passportDataService;
  private final CarRepairService carRepairService;

  public ProfileController(UserService userService,
      CarService carService, CarOrderService carOrderService,
      UserRoleService userRoleService,
      PassportDataService passportDataService,
      CarRepairService carRepairService) {
    this.userService = userService;
    this.carService = carService;
    this.carOrderService = carOrderService;
    this.userRoleService = userRoleService;
    this.passportDataService = passportDataService;
    this.carRepairService = carRepairService;
  }

  @GetMapping("/profile")
  public String getProfileMenu() {
    return "profile";
  }

  @GetMapping("/profile/information")
  public String getInformation(Model model, @AuthenticationPrincipal UserDetails currentUser) {
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

  @GetMapping("/profile/order/{id}/reason")
  public String getOrderCancellationReason(@PathVariable Integer id, Model model) {
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "reason";
  }

  @GetMapping("/profile/order/{id}/detail")
  public String getOrderDetail(@PathVariable Integer id, Model model) {
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "detail";
  }

  @GetMapping("/profile/order/{id}/cancellation")
  public String getOrderCancellation(@PathVariable Integer id, Model model) {
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "cancellation";
  }

  @PostMapping("/profile/order/{id}/cancellation")
  public String saveReasonOfCancellation(@PathVariable Integer id,
      @RequestParam("reasonOfCancellation") String reasonOfCancellation) {
    carOrderService.cancelCarOrder(reasonOfCancellation, id);
    return "redirect:/profile/order";
  }

  @GetMapping("/profile/user")
  public String getAllUser(Model model) {
    List<User> users = userService.getAll();
    model.addAttribute("users", users);
    return "user";
  }

  @GetMapping("/profile/user/{id}/edit")
  public String getEditUser(@PathVariable Integer id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "edit_user";
  }

  @PostMapping("/profile/user/{id}/editUser")
  public String saveEditUser(@PathVariable Integer id,
      @RequestParam("username") String username,
      @RequestParam("email") String email,
      @RequestParam("phoneNumber") String phoneNumber,
      @RequestParam("userRole") String userRole,
      @RequestParam("status") String status) {
    UserPostDto userPostDto = new UserPostDto();
    if (userService.isUsernameExistsExceptUsernameWithId(username, id) ||
        userService.isEmailExistsExceptEmailWithId(email, id) || userService
        .isPhoneNumberExistsExceptPhoneNumberWithId(phoneNumber, id)
    ) {
      return "redirect:/profile/user/{id}/edit";
    }
    userPostDto.setId(id);
    userPostDto.setUsername(username);
    userPostDto.setEmail(email);
    userPostDto.setPhoneNumber(phoneNumber);
    userPostDto.setUserRole(userRoleService.findByName(userRole));
    userPostDto.setStatus(status);
    userService.update(userPostDto);
    return "redirect:/profile/user";
  }

  @PostMapping("/profile/user/{id}/editUserPassword")
  public String changeUserPassword(@PathVariable Integer id,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm) {
    if (!password.equals(passwordConfirm)) {
      return "redirect:/profile/user/{id}/edit";
    }
    userService.changeUserPassword(userService.findById(id), password);
    return "redirect:/profile/user";
  }

  @GetMapping("/profile/user/{id}/passport")
  public String getPassport(@PathVariable Integer id, Model model) {
    User user = userService.findById(id);
    PassportData passportData = user.getPassportData();
    model.addAttribute("passportData", passportData);
    return "passport";
  }

  @GetMapping("/profile/user/{userId}/passport/{id}/edit")
  public String getEditPassport(@PathVariable Integer id, @PathVariable Integer userId,
      Model model) {
    User user = userService.findById(userId);
    PassportData passportData = passportDataService.findById(id);
    model.addAttribute("user", user);
    model.addAttribute("passportData", passportData);
    return "edit_passport";
  }

  @PostMapping("/profile/user/{userId}/passport/{id}/edit")
  public String saveEditPassport(
      @ModelAttribute("passportDataDto") PassportDataPostDto passportDataPostDto,
      BindingResult bindingResult, @PathVariable Integer id, @PathVariable Integer userId) {
    if (passportDataService
        .isPassportNumberExistsExceptPassportNumberWithId(passportDataPostDto.getPassportNumber(),
            id) ||
        passportDataService.isIdentificationNumberExistsExceptIdentificationNumberWithId(
            passportDataPostDto.getIdentificationNumber(), id)) {
      return "redirect:/profile/user/{userId}/passport/{id}/edit";
    }
    passportDataService.update(passportDataPostDto);
    return "redirect:/profile/user/{userId}/passport";
  }

  @GetMapping("/profile/repair")
  public String getAllRepair(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    List<CarRepair> carRepairs = carRepairService.getAll(currentUser);
    model.addAttribute("carRepairs", carRepairs);
    return "repair";
  }

  @GetMapping("/profile/order/{id}/repair")
  public String getNewRepair(@PathVariable Integer id, Model model) {
    CarOrder carOrder = carOrderService.findById(id);
    model.addAttribute("carOrder", carOrder);
    return "new_repair";
  }

  @PostMapping("/profile/order/{id}/repair")
  public String saveNewRepair(@RequestParam("repairCost") Double repairCost,
      @RequestParam("damageInformation") String damageInformation, @PathVariable Integer id) {
    CarRepair carRepair = new CarRepair();
    carRepair.setCarOrder(carOrderService.findById(id));
    carRepair.setDamageInformation(damageInformation);
    carRepair.setRepairCost(repairCost);
    carRepairService.save(carRepair);
    return "redirect:/profile/order";
  }

  @GetMapping("/profile/car")
  public String getAllCar(Model model) {
    List<Car> cars = carService.getAll();
    model.addAttribute("cars", cars);
    return "car_list";
  }

  @GetMapping("/profile/car/{id}/detail")
  public String getCarDetail(@PathVariable Integer id, Model model) {
    Car car = carService.findById(id);
    model.addAttribute("car", car);
    return "car_detail";
  }

  @GetMapping("/profile/car/{id}/edit")
  public String getEditCar(@PathVariable Integer id, Model model){
    Car car = carService.findById(id);
    model.addAttribute("car", car);
    return "car_edit";
  }


}
