package com.kazak.carrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {

  @GetMapping("/index")
  public String mainPage(){
    return "index";
  }

  @GetMapping("/catalog")
  public String catalogPage(){
    return "catalog";
  }

  @GetMapping("/condition")
  public String conditionPage(){
    return "condition";
  }

  @GetMapping("/contact")
  public String contactPage(){
    return "contact";
  }

}
