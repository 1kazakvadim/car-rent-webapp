package com.kazak.carrent.controller;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.model.entity.UserRole;
import com.kazak.carrent.service.PassportDataService;
import com.kazak.carrent.service.UserRoleService;
import com.kazak.carrent.service.UserService;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

  private final UserService userService;
  private final PassportDataService passportDataService;
  private final UserRoleService userRoleService;
  private final MessageSource messageSource;


  public UserController(UserService userService,
      PassportDataService passportDataService,
      UserRoleService userRoleService, MessageSource messageSource) {
    this.userService = userService;
    this.passportDataService = passportDataService;
    this.userRoleService = userRoleService;
    this.messageSource = messageSource;
  }

  @GetMapping("/profile/user/{userId}/edit")
  public String getUserEdit(@PathVariable Integer userId, Model model) {
    User user = userService.findById(userId);
    List<UserRole> userRoles = userRoleService.getAll();
    model.addAttribute("user", user);
    model.addAttribute("userRoles", userRoles);
    return "user/user_edit";
  }

  @PostMapping("/profile/user/{userId}/edit")
  public String saveEditUser(@PathVariable Integer userId,
      @RequestParam("username") String username,
      @RequestParam("email") String email,
      @RequestParam("phoneNumber") String phoneNumber,
      @RequestParam("userRole") String userRole,
      @RequestParam("status") String status,
      RedirectAttributes RedirectAttributes, Locale locale) {
    UserPostDto userPostDto = new UserPostDto();
    if (userService.isUsernameExistsExceptUsernameWithId(username, userId) ||
        userService.isEmailExistsExceptEmailWithId(email, userId) || userService
        .isPhoneNumberExistsExceptPhoneNumberWithId(phoneNumber, userId)
    ) {
      RedirectAttributes
          .addFlashAttribute("invalidUserEdit",
              messageSource.getMessage("error.invalidUserEdit", null, locale));
      return "redirect:/profile/user/{userId}/edit";
    }
    userPostDto.setId(userId);
    userPostDto.setUsername(username);
    userPostDto.setEmail(email);
    userPostDto.setPhoneNumber(phoneNumber);
    userPostDto.setUserRole(userRoleService.findByName(userRole));
    userPostDto.setStatus(status);
    userService.update(userPostDto);
    RedirectAttributes
        .addFlashAttribute("userEdit",
            messageSource.getMessage("notification.userEdit", null, locale));
    return "redirect:/profile/user/{userId}/edit";
  }

  @PostMapping("/profile/user/{userId}/password")
  public String changeUserPasswordByAdmin(@PathVariable Integer userId,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm,
      RedirectAttributes RedirectAttributes, Locale locale) {
    if (!password.equals(passwordConfirm)) {
      RedirectAttributes
          .addFlashAttribute("wrongPassword",
              messageSource.getMessage("error.wrongPassword", null, locale));
      return "redirect:/profile/user/{userId}/edit";
    }
    userService.changeUserPassword(userService.findById(userId), password);
    RedirectAttributes
        .addFlashAttribute("passwordChange",
            messageSource.getMessage("notification.passwordChange", null, locale));
    return "redirect:/profile/user/{userId}/edit";
  }

  @GetMapping("/profile/user/{userId}/passport")
  public String getUserPassport(@PathVariable Integer userId, Model model) {
    User user = userService.findById(userId);
    PassportData passportData = user.getPassportData();
    model.addAttribute("passportData", passportData);
    return "user/user_passport";
  }

  @GetMapping("/profile/user/{userId}/passport/{passportId}/edit")
  public String getPassportEdit(@PathVariable Integer passportId, @PathVariable Integer userId,
      Model model) {
    User user = userService.findById(userId);
    PassportData passportData = passportDataService.findById(passportId);
    model.addAttribute("user", user);
    model.addAttribute("passportData", passportData);
    return "user/passport_edit";
  }

  @PostMapping("/profile/user/{userId}/passport/{passportId}/edit")
  public String saveEditPassport(
      @ModelAttribute("passportDataDto") PassportDataPostDto passportDataPostDto,
      @PathVariable Integer passportId, @PathVariable Integer userId,
      RedirectAttributes RedirectAttributes, Locale locale) {
    if (passportDataService
        .isPassportNumberExistsExceptPassportNumberWithId(passportDataPostDto.getPassportNumber(),
            passportId) ||
        passportDataService.isIdentificationNumberExistsExceptIdentificationNumberWithId(
            passportDataPostDto.getIdentificationNumber(), passportId)) {
      RedirectAttributes
          .addFlashAttribute("invalidPassportEdit",
              messageSource.getMessage("error.invalidPassportEdit", null, locale));
      return "redirect:/profile/user/{userId}/passport/{passportId}/edit";
    }
    passportDataService.update(passportDataPostDto);
    RedirectAttributes
        .addFlashAttribute("passportEdit",
            messageSource.getMessage("notification.passportEdit", null, locale));
    return "redirect:/profile/user/{userId}/passport/{passportId}/edit";
  }

}
