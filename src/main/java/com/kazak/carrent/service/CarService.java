package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import java.util.List;
import org.springframework.ui.Model;

public interface CarService {

  List<Car> getAll();

  Car findById(Integer id);

  List<Car> findByModel(String model);

  Model getFilteredCarList(List<String> carBrandsFiltered, List<String> carModelsFiltered,
      List<String> carBodiesFiltered, List<String> carClassesFiltered,
      String carTransmissionFiltered, Model model);

}
