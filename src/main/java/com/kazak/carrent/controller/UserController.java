package com.kazak.carrent.controller;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.dto.UserPostDto;
import com.kazak.carrent.service.PassportDataService;
import com.kazak.carrent.service.UserRoleService;
import com.kazak.carrent.service.UserService;
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

  @GetMapping("/profile/users/{userId}/edit")
  public String getUserEdit(@PathVariable Integer userId, Model model) {
    model.addAttribute("user", new UserPostDto());
    model.addAttribute("user", userService.findById(userId));
    model.addAttribute("userRoles", userRoleService.getAll());
    return "user/user_edit";
  }

  @PostMapping("/profile/users/{userId}/edit")
  public String saveEditUser(@PathVariable Integer userId,
      @ModelAttribute("user") UserPostDto userPostDto,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (userService.isUsernameExists(userPostDto.getUsername(), userId) ||
        userService.isEmailExists(userPostDto.getEmail(), userId) || userService
        .isPhoneNumberExists(userPostDto.getPhoneNumber(), userId)
    ) {
      redirectAttributes
          .addFlashAttribute("invalidUserEdit",
              messageSource.getMessage("error.invalidUserEdit", null, locale));
      return "redirect:/profile/users/{userId}/edit";
    }
    userService.update(userPostDto, userId);
    redirectAttributes
        .addFlashAttribute("userEdit",
            messageSource.getMessage("notification.userEdit", null, locale));
    return "redirect:/profile/users/{userId}/edit";
  }

  @PostMapping("/profile/users/{userId}/password")
  public String changeUserPasswordByAdmin(@PathVariable Integer userId,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (!userService.changeUserPassword(userId, password, passwordConfirm)) {
      redirectAttributes
          .addFlashAttribute("wrongPassword",
              messageSource.getMessage("error.wrongPassword", null, locale));
    } else {
      redirectAttributes
          .addFlashAttribute("passwordChange",
              messageSource.getMessage("notification.passwordChange", null, locale));
    }
    return "redirect:/profile/users/{userId}/edit";
  }

  @GetMapping("/profile/users/{userId}/passport")
  public String getUserPassport(@PathVariable Integer userId, Model model) {
    model.addAttribute("passportData", userService.findById(userId).getPassportData());
    return "user/user_passport";
  }

  @GetMapping("/profile/users/{userId}/passport/{passportId}/edit")
  public String getPassportEdit(@PathVariable Integer passportId, @PathVariable Integer userId,
      Model model) {
    model.addAttribute("user", userService.findById(userId));
    model.addAttribute("passportData", passportDataService.findById(passportId));
    return "user/passport_edit";
  }

  @PostMapping("/profile/users/{userId}/passport/{passportId}/edit")
  public String saveEditPassport(
      @ModelAttribute("passport") PassportDataPostDto passportDataPostDto,
      @PathVariable Integer passportId, @PathVariable Integer userId,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (passportDataService
        .isPassportNumberExists(passportDataPostDto.getPassportNumber(),
            passportId) ||
        passportDataService.isIdentificationNumberExists(
            passportDataPostDto.getIdentificationNumber(), passportId)) {
      redirectAttributes
          .addFlashAttribute("invalidPassportEdit",
              messageSource.getMessage("error.invalidPassportEdit", null, locale));
      return "redirect:/profile/users/{userId}/passport/{passportId}/edit";
    }
    passportDataService.update(passportDataPostDto, passportId);
    redirectAttributes
        .addFlashAttribute("passportEdit",
            messageSource.getMessage("notification.passportEdit", null, locale));
    return "redirect:/profile/users/{userId}/passport/{passportId}/edit";
  }

}
