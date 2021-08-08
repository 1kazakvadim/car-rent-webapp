package com.kazak.carrent.service;

import com.kazak.carrent.dto.CarPostDto;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface CarService {

  List<Car> getAll();

  Car findById(Integer id);

  List<Car> findByModel(String model);

  void save(Car car, MultipartFile imageFile);

  void update(Integer carId, CarPostDto carPostDto, MultipartFile imageFile);

  List<Car> getFilteredCarList(List<CarBrand> carBrandsFiltered, List<String> carModelsFiltered,
      List<CarBody> carBodiesFiltered, List<CarClass> carClassesFiltered,
      List<CarTransmission> carTransmissionsFiltered);

}
