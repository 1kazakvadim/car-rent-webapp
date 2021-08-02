package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.repository.CarBodyRepository;
import com.kazak.carrent.repository.CarBrandRepository;
import com.kazak.carrent.repository.CarClassRepository;
import com.kazak.carrent.repository.CarRepository;
import com.kazak.carrent.repository.CarTransmissionRepository;
import com.kazak.carrent.service.CarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final CarClassRepository carClassRepository;
  private final CarBodyRepository carBodyRepository;
  private final CarBrandRepository carBrandRepository;
  private final CarTransmissionRepository carTransmissionRepository;


  public CarServiceImpl(CarRepository carRepository,
      CarClassRepository carClassRepository,
      CarBodyRepository carBodyRepository,
      CarBrandRepository carBrandRepository,
      CarTransmissionRepository carTransmissionRepository) {
    this.carRepository = carRepository;
    this.carClassRepository = carClassRepository;
    this.carBodyRepository = carBodyRepository;
    this.carBrandRepository = carBrandRepository;
    this.carTransmissionRepository = carTransmissionRepository;
  }

  @Override
  public List<Car> getAll() {
    return carRepository.findAll();
  }

  @Override
  public Car findById(Integer id) {
    return carRepository.findById(id).orElse(null);
  }

  @Override
  public List<Car> findByModel(String model) {
    return carRepository.findByModel(model);
  }

  @Override
  public List<Car> getFilteredCarList(List<String> carBrandsFiltered,
      List<String> carModelsFiltered,
      List<String> carBodiesFiltered, List<String> carClassesFiltered,
      List<String> carTransmissionsFiltered) {

    List<CarBody> carBodiesTemp = new ArrayList<>();
    List<String> carModelsTemp = new ArrayList<>();
    List<CarClass> carClassesTemp = new ArrayList<>();
    List<CarBrand> carBrandsTemp = new ArrayList<>();
    List<CarTransmission> carTransmissionsTemp = new ArrayList<>();

    if (carBrandsFiltered != null) {
      for (String carBrandName : carBrandsFiltered) {
        carBrandsTemp.add(carBrandRepository.findByName(carBrandName));
      }
    } else {
      carBrandsTemp = carBrandRepository.findAll();
    }
    if (carModelsFiltered != null) {
      for (String carModelName : carModelsFiltered) {
        List<Car> cars = carRepository.findByModel(carModelName);
        for (Car car : cars) {
          carModelsTemp.add(car.getModel());
        }
      }
    } else {
      List<Car> cars = carRepository.findAll();
      for (Car car : cars) {
        carModelsTemp.add(car.getModel());
      }
    }
    if (carBodiesFiltered != null) {
      for (String carBodyName : carBodiesFiltered) {
        carBodiesTemp.add(carBodyRepository.findByName(carBodyName));
      }
    } else {
      carBodiesTemp = carBodyRepository.findAll();
    }
    if (carClassesFiltered != null) {
      for (String carClassName : carClassesFiltered) {
        carClassesTemp.add(carClassRepository.findByName(carClassName));
      }
    } else {
      carClassesTemp = carClassRepository.findAll();
    }
    if (carTransmissionsFiltered != null) {
      for (String carTransmissionName : carTransmissionsFiltered) {
        carTransmissionsTemp.add(carTransmissionRepository.findByName(carTransmissionName));
      }
    } else {
      carTransmissionsTemp = carTransmissionRepository.findAll();
    }

    return carRepository
        .findByCarBrandInAndModelInAndCarBodyInAndCarClassInAndCarTransmissionIn(carBrandsTemp,
            carModelsTemp, carBodiesTemp, carClassesTemp,
            carTransmissionsTemp);
  }

}
