package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import com.kazak.carrent.service.EngineTypeService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CarController {

  private final CarService carService;
  private final CarBrandService carBrandService;
  private final CarBodyService carBodyService;
  private final CarClassService carClassService;
  private final CarTransmissionService carTransmissionService;
  private final EngineTypeService engineTypeService;


  public CarController(CarService carService,
      CarBrandService carBrandService, CarBodyService carBodyService,
      CarClassService carClassService,
      CarTransmissionService carTransmissionService,
      EngineTypeService engineTypeService) {
    this.carService = carService;
    this.carBrandService = carBrandService;
    this.carBodyService = carBodyService;
    this.carClassService = carClassService;
    this.carTransmissionService = carTransmissionService;
    this.engineTypeService = engineTypeService;
  }

  @GetMapping("/profile/car/{carId}/detail")
  public String getCarDetail(@PathVariable Integer carId, Model model) {
    Car car = carService.findById(carId);
    model.addAttribute("car", car);
    return "car/car_detail";
  }

  @GetMapping("/profile/car/{carId}/edit")
  public String getEditCar(@PathVariable Integer carId, Model model) {
    Car car = carService.findById(carId);
    model.addAttribute("car", car);
    return "car/car_edit";
  }

  @GetMapping("/profile/car/new")
  public String addNewCar(Model model) {
    List<CarBrand> carBrands = carBrandService.getAll();
    List<CarBody> carBodies = carBodyService.getAll();
    List<CarClass> carClasses = carClassService.getAll();
    List<CarTransmission> carTransmissions = carTransmissionService.getAll();
    List<EngineType> engineTypes = engineTypeService.getAll();
    model.addAttribute("carBrands", carBrands);
    model.addAttribute("carBodies", carBodies);
    model.addAttribute("carClasses", carClasses);
    model.addAttribute("carTransmissions", carTransmissions);
    model.addAttribute("engineTypes", engineTypes);
    return "car/car_new";
  }

  @PostMapping("/profile/car/new")
  public String saveNewCar(
      @RequestParam("carBrand") String carBrand,
      @RequestParam("carModel") String carModel,
      @RequestParam("carBody") String carBody,
      @RequestParam("color") String color,
      @RequestParam("carClass") String carClass,
      @RequestParam("carTransmission") String carTransmission,
      @RequestParam("engineType") String engineType,
      @RequestParam("engineVolume") Double engineVolume,
      @RequestParam("numberOfSeats") Integer numberOfSeats,
      @RequestParam("fuelConsumption") Double fuelConsumption,
      @RequestParam("rentalCost") Double rentalCost,
      @RequestParam("imageFile") MultipartFile imageFile
  ) {
    carService.save(carBrand, carModel, carBody, color, carClass, carTransmission, engineType,
        engineVolume, numberOfSeats, fuelConsumption, rentalCost, imageFile);
    return "redirect:/profile/car";
  }

}
