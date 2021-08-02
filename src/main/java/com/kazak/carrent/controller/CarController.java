package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CarController {

  private final CarService carService;

  public CarController(CarService carService) {
    this.carService = carService;
  }

  @GetMapping("/profile/car/{carId}/detail")
  public String getCarDetail(@PathVariable Integer carId, Model model) {
    Car car = carService.findById(carId);
    model.addAttribute("car", car);
    return "car/car_detail";
  }

  @GetMapping("/profile/car/{carId}/edit")
  public String getEditCar(@PathVariable Integer carId, Model model){
    Car car = carService.findById(carId);
    model.addAttribute("car", car);
    return "car/car_edit";
  }
}
