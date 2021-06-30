package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CatalogController {

  private final CarService carService;
  private final CarBodyService carBodyService;
  private final CarClassService carClassService;
  private final CarBrandService carBrandService;
  private final CarTransmissionService carTransmissionService;

  public CatalogController(CarService carService, CarBodyService carBodyService,
      CarClassService carClassService, CarBrandService carBrandService,
      CarTransmissionService carTransmissionService) {
    this.carService = carService;
    this.carBodyService = carBodyService;
    this.carClassService = carClassService;
    this.carBrandService = carBrandService;
    this.carTransmissionService = carTransmissionService;
  }

  @GetMapping("/catalog")
  public String catalogPage(Model model) {
    List<Car> cars = carService.getAll();
    List<CarBody> carBodies = carBodyService.getAll();
    List<CarClass> carClasses = carClassService.getAll();
    List<CarBrand> carBrands = carBrandService.getAll();
    List<CarTransmission> carTransmissions = carTransmissionService.getAll();
    model.addAttribute("cars", cars);
    model.addAttribute("carBodies", carBodies);
    model.addAttribute("carClasses", carClasses);
    model.addAttribute("carBrands", carBrands);
    model.addAttribute("carTransmissions", carTransmissions);
    return "catalog";
  }

  @GetMapping("/catalog/{id}")
  public String getCar(@PathVariable Integer id, Model model) {
    Car car = carService.findById(id);
    model.addAttribute("car", car);
    return "product";
  }

  @PostMapping("/catalog/{id}")
  public String bookCar(@PathVariable Integer id, Model model) {
    return "profile";
  }

}
