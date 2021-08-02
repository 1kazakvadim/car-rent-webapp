package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

  private final CarOrderService carOrderService;
  private final CarRepairService carRepairService;

  public OrderController(CarOrderService carOrderService,
      CarRepairService carRepairService) {
    this.carOrderService = carOrderService;
    this.carRepairService = carRepairService;
  }

  @GetMapping("/profile/order/{orderId}/reason")
  public String getOrderCancellationReason(@PathVariable Integer orderId, Model model) {
    CarOrder carOrder = carOrderService.findById(orderId);
    model.addAttribute("carOrder", carOrder);
    return "order/order_reason";
  }

  @GetMapping("/profile/order/{orderId}/detail")
  public String getOrderDetail(@PathVariable Integer orderId, Model model) {
    CarOrder carOrder = carOrderService.findById(orderId);
    model.addAttribute("carOrder", carOrder);
    return "order/order_detail";
  }

  @GetMapping("/profile/order/{orderId}/cancellation")
  public String getOrderCancellation(@PathVariable Integer orderId, Model model) {
    CarOrder carOrder = carOrderService.findById(orderId);
    model.addAttribute("carOrder", carOrder);
    return "order/order_cancellation";
  }

  @PostMapping("/profile/order/{orderId}/cancellation")
  public String saveReasonOfCancellation(@PathVariable Integer orderId,
      @RequestParam("reasonOfCancellation") String reasonOfCancellation) {
    carOrderService.cancelCarOrder(reasonOfCancellation, orderId);
    return "redirect:/profile/order";
  }

  @GetMapping("/profile/order/{orderId}/repair")
  public String addRepair(@PathVariable Integer orderId, Model model) {
    CarOrder carOrder = carOrderService.findById(orderId);
    model.addAttribute("carOrder", carOrder);
    return "order/order_repair";
  }

  @PostMapping("/profile/order/{orderId}/repair")
  public String saveRepair(@RequestParam("repairCost") Double repairCost,
      @RequestParam("damageInformation") String damageInformation, @PathVariable Integer orderId) {
    CarRepair carRepair = new CarRepair();
    carRepair.setCarOrder(carOrderService.findById(orderId));
    carRepair.setDamageInformation(damageInformation);
    carRepair.setRepairCost(repairCost);
    carRepairService.save(carRepair);
    return "redirect:/profile/order";
  }

}
