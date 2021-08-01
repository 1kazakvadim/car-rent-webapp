package com.kazak.carrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConditionController {

  @GetMapping("/condition")
  public String getConditionPage() {
    return "condition";
  }

}
