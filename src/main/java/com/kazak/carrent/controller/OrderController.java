package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarRepairService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

  private final CarOrderService carOrderService;
  private final CarRepairService carRepairService;
  private final MessageSource messageSource;

  public OrderController(CarOrderService carOrderService,
      CarRepairService carRepairService, MessageSource messageSource) {
    this.carOrderService = carOrderService;
    this.carRepairService = carRepairService;
    this.messageSource = messageSource;
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
      @RequestParam("reasonOfCancellation") String reasonOfCancellation,
      RedirectAttributes redirectAttributes, Locale locale) {
    carOrderService.cancelCarOrder(reasonOfCancellation, orderId);
    redirectAttributes
        .addFlashAttribute("cancellation",
            messageSource.getMessage("notification.carCancellation", null, locale));
    return "redirect:/profile/order/{orderId}/cancellation";
  }

  @GetMapping("/profile/order/{orderId}/repair")
  public String addRepair(@PathVariable Integer orderId, Model model) {
    CarOrder carOrder = carOrderService.findById(orderId);
    model.addAttribute("carOrder", carOrder);
    return "order/order_repair";
  }

  @PostMapping("/profile/order/{orderId}/repair")
  public String saveRepair(@RequestParam("repairCost") Double repairCost,
      @RequestParam("damageInformation") String damageInformation, @PathVariable Integer orderId,
      RedirectAttributes redirectAttributes, Locale locale) {
    CarRepair carRepair = new CarRepair();
    carRepair.setCarOrder(carOrderService.findById(orderId));
    carRepair.setDamageInformation(damageInformation);
    carRepair.setRepairCost(repairCost);
    carRepairService.save(carRepair);
    redirectAttributes
        .addFlashAttribute("repairAdd",
            messageSource.getMessage("notification.repairAdd", null, locale));
    return "redirect:/profile/order/{orderId}/repair";
  }

}
