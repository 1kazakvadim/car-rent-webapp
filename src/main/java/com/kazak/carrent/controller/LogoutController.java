package com.kazak.carrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

  @RequestMapping("/logout")
  public String logoutPage() {
    return "index";
  }

}
