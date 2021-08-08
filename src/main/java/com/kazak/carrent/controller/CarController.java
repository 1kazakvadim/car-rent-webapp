package com.kazak.carrent.controller;

import com.kazak.carrent.dto.CarPostDto;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import com.kazak.carrent.service.EngineTypeService;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarController {

  private final CarService carService;
  private final CarBrandService carBrandService;
  private final CarBodyService carBodyService;
  private final CarClassService carClassService;
  private final CarTransmissionService carTransmissionService;
  private final EngineTypeService engineTypeService;
  private final MessageSource messageSource;

  public CarController(CarService carService,
      CarBrandService carBrandService, CarBodyService carBodyService,
      CarClassService carClassService,
      CarTransmissionService carTransmissionService,
      EngineTypeService engineTypeService,
      MessageSource messageSource) {
    this.carService = carService;
    this.carBrandService = carBrandService;
    this.carBodyService = carBodyService;
    this.carClassService = carClassService;
    this.carTransmissionService = carTransmissionService;
    this.engineTypeService = engineTypeService;
    this.messageSource = messageSource;
  }


  @GetMapping("/profile/cars/{carId}/detail")
  public String getCarDetail(@PathVariable Integer carId, Model model) {
    model.addAttribute("car", carService.findById(carId));
    return "car/car_detail";
  }

  @GetMapping("/profile/cars/{carId}/edit")
  public String getEditCar(@PathVariable Integer carId, Model model) {
    model.addAttribute("carBrands", carBrandService.getAll());
    model.addAttribute("carBodies", carBodyService.getAll());
    model.addAttribute("carClasses", carClassService.getAll());
    model.addAttribute("carTransmissions", carTransmissionService.getAll());
    model.addAttribute("engineTypes", engineTypeService.getAll());
    model.addAttribute("car", carService.findById(carId));
    return "car/car_edit";
  }

  @PostMapping("/profile/cars/{carId}/edit")
  public String saveEditCar(@PathVariable Integer carId,
      @ModelAttribute("car") CarPostDto carPostDto,
      @RequestParam("imageFile") MultipartFile imageFile,
      RedirectAttributes redirectAttributes, Locale locale) {
    carService.update(carId, carPostDto, imageFile);
    redirectAttributes
        .addFlashAttribute("carEdit",
            messageSource.getMessage("notification.carEdit", null, locale));
    return "redirect:/profile/cars/{carId}/edit";
  }

  @GetMapping("/profile/cars/new")
  public String addNewCar(Model model) {
    model.addAttribute("car", new Car());
    model.addAttribute("carBrands", carBrandService.getAll());
    model.addAttribute("carBodies", carBodyService.getAll());
    model.addAttribute("carClasses", carClassService.getAll());
    model.addAttribute("carTransmissions", carTransmissionService.getAll());
    model.addAttribute("engineTypes", engineTypeService.getAll());
    return "car/car_new";
  }

  @PostMapping("/profile/cars/new")
  public String saveNewCar(
      @ModelAttribute("car") Car car,
      @RequestParam("imageFile") MultipartFile imageFile,
      RedirectAttributes redirectAttributes, Locale locale) {
    carService.save(car, imageFile);
    redirectAttributes
        .addFlashAttribute("carAdd",
            messageSource.getMessage("notification.carAdd", null, locale));
    return "redirect:/profile/cars/new";
  }

}
