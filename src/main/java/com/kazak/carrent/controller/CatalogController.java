package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.service.CarService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CatalogController {

  private final CarService carService;

  public CatalogController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping("/catalog")
  public String catalogPage(Model model){
    List<Car> cars = carService.getAll();
    model.addAttribute("cars", cars);
    return "catalog";
  }

  @GetMapping("/catalog/{id}")
  public String getCar(@PathVariable Integer id, Model model){
    Car car = carService.findById(id);
    model.addAttribute("car", car);
    return "product";
  }

}
