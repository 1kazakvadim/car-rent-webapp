package com.kazak.carrent.controller;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarOrderService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import com.kazak.carrent.service.UserService;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatalogController {

  private final CarService carService;
  private final CarBodyService carBodyService;
  private final CarClassService carClassService;
  private final CarBrandService carBrandService;
  private final CarTransmissionService carTransmissionService;
  private final CarOrderService carOrderService;
  private final UserService userService;

  public CatalogController(CarService carService, CarBodyService carBodyService,
      CarClassService carClassService, CarBrandService carBrandService,
      CarTransmissionService carTransmissionService,
      CarOrderService carOrderService, UserService userService) {
    this.carService = carService;
    this.carBodyService = carBodyService;
    this.carClassService = carClassService;
    this.carBrandService = carBrandService;
    this.carTransmissionService = carTransmissionService;
    this.carOrderService = carOrderService;
    this.userService = userService;
  }

  @GetMapping("/catalog")
  public String getCatalogPage(Model model) {
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

  @PostMapping("/catalog/filtered")
  public String getFilteredCatalogPage(
      @RequestParam(required = false) List<String> carBrandsFiltered,
      @RequestParam(required = false) List<String> carModelsFiltered,
      @RequestParam(required = false) List<String> carBodiesFiltered,
      @RequestParam(required = false) List<String> carClassesFiltered,
      @RequestParam(required = false) String carTransmissionsFiltered,
      Model model) {

    model = carService.getFilteredCarList(carBrandsFiltered, carModelsFiltered, carBodiesFiltered,
        carClassesFiltered, carTransmissionsFiltered, model);

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
    return "catalog_filtered";
  }

  @GetMapping("/catalog/{carId}/detail")
  public String getCar(@PathVariable Integer carId, Model model) {
    Car car = carService.findById(carId);
    model.addAttribute("car", car);
    return "catalog_detail";
  }

  @PostMapping("/catalog/{carId}/detail/")
  public String bookCar(@AuthenticationPrincipal UserDetails currentUser,
      @PathVariable Integer carId,
      @RequestParam("dateOfIssue") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfIssue,
      @RequestParam("dateOfReturn") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfReturn,
      @RequestParam("carDetailId") Integer carDetailId, CarOrder carOrder) {
    if (currentUser == null) {
      return "redirect:/login";
    }
    carOrderService.save(carOrder, carDetailId, currentUser);
    return "redirect:/profile/order";
  }

}
