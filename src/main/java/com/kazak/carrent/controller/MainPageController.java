package com.kazak.carrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

  @GetMapping("/index")
  public String mainPage() {
    return "index";
  }

}
