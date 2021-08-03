package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.service.CarRepairService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RepairController {

  private final CarRepairService carRepairService;

  public RepairController(CarRepairService carRepairService) {
    this.carRepairService = carRepairService;
  }

  @GetMapping("/profile/repair/{repairId}/damage-information")
  public String getRepairDamageInformation(@PathVariable Integer repairId, Model model) {
    CarRepair carRepair = carRepairService.findById(repairId);
    model.addAttribute("carRepair", carRepair);
    return "repair/repair_damage_information";
  }

}
