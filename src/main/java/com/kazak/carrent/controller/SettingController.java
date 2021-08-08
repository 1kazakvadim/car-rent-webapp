package com.kazak.carrent.controller;

import com.kazak.carrent.service.UserService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SettingController {

  private final UserService userService;
  private final MessageSource messageSource;

  public SettingController(UserService userService,
      MessageSource messageSource) {
    this.userService = userService;
    this.messageSource = messageSource;
  }

  @PostMapping("/profile/settings/password")
  public String changeUserPasswordByUser(@AuthenticationPrincipal UserDetails currentUser,
      @RequestParam("passwordOld") String passwordOld,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (!userService.changeUserPasswordByUser(currentUser, passwordOld, password, passwordConfirm)) {
      redirectAttributes
          .addFlashAttribute("wrongPassword",
              messageSource.getMessage("error.wrongPassword", null, locale));
      return "redirect:/profile/settings";
    } else {
      redirectAttributes
          .addFlashAttribute("passwordChange",
              messageSource.getMessage("notification.passwordChange", null, locale));
    }
    return "redirect:/profile/settings";
  }

}
