package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.service.UserService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SettingController {

  private final UserService userService;
  private final MessageSource messageSource;
  private final PasswordEncoder passwordEncoder;

  public SettingController(UserService userService,
      MessageSource messageSource, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.messageSource = messageSource;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/profile/setting/password")
  public String changeUserPasswordByUser(@AuthenticationPrincipal UserDetails currentUser,
      @RequestParam("passwordOld") String passwordOld,
      @RequestParam("password") String password,
      @RequestParam("passwordConfirm") String passwordConfirm,
      RedirectAttributes RedirectAttributes, Locale locale) {
    User user = userService.findByUsername(currentUser.getUsername());
    if (!password.equals(passwordConfirm) || !passwordEncoder
        .matches(passwordOld, user.getPassword())) {
      RedirectAttributes
          .addFlashAttribute("wrongPassword",
              messageSource.getMessage("error.wrongPassword", null, locale));
      return "redirect:/profile/setting";
    }
    userService.changeUserPassword(user, password);
    RedirectAttributes
        .addFlashAttribute("passwordChange",
            messageSource.getMessage("notification.passwordChange", null, locale));
    return "redirect:/profile/setting";
  }

}
