package com.kazak.carrent.controller;

import com.kazak.carrent.service.CarRepairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class RepairController {

  private final CarRepairService carRepairService;


  @GetMapping("/profile/repairs/{repairId}/damage-information")
  public String getRepairDamageInformation(@PathVariable Integer repairId, Model model) {
    model.addAttribute("carRepair", carRepairService.findById(repairId));
    return "repair/repair_damage_information";
  }

}
