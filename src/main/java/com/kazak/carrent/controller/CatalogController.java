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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CatalogController {

  private final CarService carService;
  private final CarBodyService carBodyService;
  private final CarClassService carClassService;
  private final CarBrandService carBrandService;
  private final CarTransmissionService carTransmissionService;
  private final CarOrderService carOrderService;
  private final MessageSource messageSource;


  @GetMapping("/catalog")
  public String getCatalogPage(Model model) {
    List<Car> cars = carService.getAll();
    List<String> models = new ArrayList<>();
    for(Car car : cars) {
      models.add(car.getModel());
    }
    model.addAttribute("cars", cars);
    model.addAttribute("models", models);
    model.addAttribute("carBodies", carBodyService.getAll());
    model.addAttribute("carClasses", carClassService.getAll());
    model.addAttribute("carBrands", carBrandService.getAll());
    model.addAttribute("carTransmissions", carTransmissionService.getAll());
    return "catalog/catalog";
  }

  @PostMapping("/catalog/filtered")
  public String getFilteredCatalogPage(
      @RequestParam(required = false) List<CarBrand> carBrandsFiltered,
      @RequestParam(required = false) List<String> carModelsFiltered,
      @RequestParam(required = false) List<CarBody> carBodiesFiltered,
      @RequestParam(required = false) List<CarClass> carClassesFiltered,
      @RequestParam(required = false) List<CarTransmission> carTransmissionsFiltered,
      Model model) {
    List<Car> carsFiltered = carService
        .getFilteredCarList(carBrandsFiltered, carModelsFiltered, carBodiesFiltered,
            carClassesFiltered, carTransmissionsFiltered);
    model.addAttribute("carsFiltered", carsFiltered);
    List<Car> cars = carService.getAll();
    List<String> models = new ArrayList<>();
    for(Car car : cars) {
      models.add(car.getModel());
    }
    model.addAttribute("models", models);
    model.addAttribute("carBodies", carBodyService.getAll());
    model.addAttribute("carClasses", carClassService.getAll());
    model.addAttribute("carBrands", carBrandService.getAll());
    model.addAttribute("carTransmissions", carTransmissionService.getAll());
    return "catalog/catalog_filtered";
  }

  @GetMapping("/catalog/{carId}/detail")
  public String getCarDetail(@PathVariable Integer carId, Model model) {
    model.addAttribute("car", carService.findById(carId));
    return "catalog/catalog_detail";
  }

  @PostMapping("/catalog/{carId}/detail/")
  public String bookCar(@AuthenticationPrincipal UserDetails currentUser,
      @PathVariable Integer carId,
      @RequestParam("dateOfIssue") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfIssue,
      @RequestParam("dateOfReturn") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfReturn,
      @RequestParam("carDetailId") Integer carDetailId, CarOrder carOrder,
      RedirectAttributes redirectAttributes, Locale locale) {
    if (currentUser == null) {
      return "redirect:/login";
    }
    if (!carOrderService.checkDate(dateOfIssue, dateOfReturn) ||
        !carOrderService.checkIsCarAvailableByDate(carId, dateOfIssue, dateOfReturn)
    ) {
      redirectAttributes
          .addFlashAttribute("invalidDate",
              messageSource.getMessage("error.invalidDate", null, locale));
      return "redirect:/catalog/{carId}/detail";
    }
    carOrderService.save(carOrder, carDetailId, currentUser);
    return "redirect:/profile/orders";
  }

}
