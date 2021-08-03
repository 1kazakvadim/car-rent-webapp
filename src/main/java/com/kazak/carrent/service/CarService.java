package com.kazak.carrent.service;

import com.kazak.carrent.dto.CarPostDto;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {

  List<Car> getAll();

  Car findById(Integer id);

  List<Car> findByModel(String model);

  void save(String carBrand, String carModel, String carBody, String color, String carClass,
      String carTransmission, String engineType, Double engineVolume, Integer numberOfSeats,
      Double fuelConsumption, Double rentalCost, MultipartFile imageFile);

  void update(CarPostDto carPostDto);

  List<Car> getFilteredCarList(List<String> carBrandsFiltered, List<String> carModelsFiltered,
      List<String> carBodiesFiltered, List<String> carClassesFiltered,
      List<String> carTransmissionsFiltered);

}
