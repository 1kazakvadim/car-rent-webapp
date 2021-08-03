package com.kazak.carrent.service.impl;

import com.kazak.carrent.dto.CarPostDto;
import com.kazak.carrent.mapper.CarMapper;
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
import com.kazak.carrent.service.EngineTypeService;
import com.kazak.carrent.service.UploadImageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CarServiceImpl implements CarService {

  private final String UPLOAD_DIR = "D:/car-rent/upload_images/";
  private final CarRepository carRepository;
  private final CarClassRepository carClassRepository;
  private final CarBodyRepository carBodyRepository;
  private final CarBrandRepository carBrandRepository;
  private final EngineTypeService engineTypeService;
  private final CarTransmissionRepository carTransmissionRepository;
  private final CarMapper carMapper;
  private final UploadImageService uploadImageService;


  public CarServiceImpl(CarRepository carRepository,
      CarClassRepository carClassRepository,
      CarBodyRepository carBodyRepository,
      CarBrandRepository carBrandRepository,
      EngineTypeService engineTypeService,
      CarTransmissionRepository carTransmissionRepository,
      CarMapper carMapper, UploadImageService uploadImageService) {
    this.carRepository = carRepository;
    this.carClassRepository = carClassRepository;
    this.carBodyRepository = carBodyRepository;
    this.carBrandRepository = carBrandRepository;
    this.engineTypeService = engineTypeService;
    this.carTransmissionRepository = carTransmissionRepository;
    this.carMapper = carMapper;
    this.uploadImageService = uploadImageService;
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
  @Transactional
  public void save(String carBrand, String carModel, String carBody, String color, String carClass,
      String carTransmission, String engineType, Double engineVolume, Integer numberOfSeats,
      Double fuelConsumption, Double rentalCost, MultipartFile imageFile) {
    Car car = new Car();
    car.setCarBrand(carBrandRepository.findByName(carBrand));
    car.setModel(carModel);
    car.setCarBody(carBodyRepository.findByName(carBody));
    car.setColor(color);
    car.setCarClass(carClassRepository.findByName(carClass));
    car.setCarTransmission(carTransmissionRepository.findByName(carTransmission));
    car.setEngineType(engineTypeService.findByName(engineType));
    car.setEngineVolume(engineVolume);
    car.setNumberOfSeats(numberOfSeats);
    car.setFuelConsumption(fuelConsumption);
    car.setRentalCost(rentalCost);
    car.setImageName(uploadImageService.upload(imageFile));
    carRepository.save(car);
  }

  @Override
  @Transactional
  public void update(CarPostDto carPostDto) {
    Car car = carRepository.getById(carPostDto.getId());
    carMapper.updateCarFromDto(carPostDto, car);
    carRepository.save(car);
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
