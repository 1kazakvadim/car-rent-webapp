package com.kazak.carrent.controller;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.PassportDataService;
import com.kazak.carrent.service.UserRoleService;
import com.kazak.carrent.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

  private final UserService userService;
  private final PassportDataService passportDataService;
  private final UserRoleService userRoleService;

  public UserController(UserService userService,
      PassportDataService passportDataService,
      UserRoleService userRoleService) {
    this.userService = userService;
    this.passportDataService = passportDataService;
    this.userRoleService = userRoleService;
  }

  @GetMapping("/profile/user/{userId}/edit")
  public String getUserEdit(@PathVariable Integer userId, Model model) {
    User user = userService.findById(userId);
    model.addAttribute("user", user);
    return "user_edit";
  }

  @PostMapping("/profile/user/{userId}/edit")
  public String saveEditUser(@PathVariable Integer userId,
      @RequestParam("username") String username,
      @RequestParam("email") String email,
      @RequestParam("phoneNumber") String phoneNumber,
      @RequestParam("userRole") String userRole,
      @RequestParam("status") String status) {
    UserPostDto userPostDto = new UserPostDto();
    if (userService.isUsernameExistsExceptUsernameWithId(username, userId) ||
        userService.isEmailExistsExceptEmailWithId(email, userId) || userService
        .isPhoneNumberExistsExceptPhoneNumberWithId(phoneNumber, userId)
    ) {
      return "redirect:/profile/user/{userId}/edit";
    }
    userPostDto.setId(userId);
    userPostDto.setUsername(username);
    userPostDto.setEmail(email);
    userPostDto.setPhoneNumber(phoneNumber);
    userPostDto.setUserRole(userRoleService.findByName(userRole));
    userPostDto.setStatus(status);
    userService.update(userPostDto);
    return "redirect:/profile/user";
  }

  @PostMapping("/profile/user/{userId}/password")
  public String changeUserPassword(@PathVariable Integer userId,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm) {
    if (!password.equals(passwordConfirm)) {
      return "redirect:/profile/user/{userId}/edit";
    }
    userService.changeUserPassword(userService.findById(userId), password);
    return "redirect:/profile/user";
  }

  @GetMapping("/profile/user/{userId}/passport")
  public String getUserPassport(@PathVariable Integer userId, Model model) {
    User user = userService.findById(userId);
    PassportData passportData = user.getPassportData();
    model.addAttribute("passportData", passportData);
    return "user_passport";
  }

  @GetMapping("/profile/user/{userId}/passport/{passportId}/edit")
  public String getPassportEdit(@PathVariable Integer passportId, @PathVariable Integer userId,
      Model model) {
    User user = userService.findById(userId);
    PassportData passportData = passportDataService.findById(passportId);
    model.addAttribute("user", user);
    model.addAttribute("passportData", passportData);
    return "passport_edit";
  }

  @PostMapping("/profile/user/{userId}/passport/{passportId}/edit")
  public String saveEditPassport(
      @ModelAttribute("passportDataDto") PassportDataPostDto passportDataPostDto,
      @PathVariable Integer passportId, @PathVariable Integer userId) {
    if (passportDataService
        .isPassportNumberExistsExceptPassportNumberWithId(passportDataPostDto.getPassportNumber(),
            passportId) ||
        passportDataService.isIdentificationNumberExistsExceptIdentificationNumberWithId(
            passportDataPostDto.getIdentificationNumber(), passportId)) {
      return "redirect:/profile/user/{userId}/passport/{passportId}/edit";
    }
    System.err.println(passportDataPostDto);
    passportDataService.update(passportDataPostDto);
    return "redirect:/profile/user/{userId}/passport";
  }

}
