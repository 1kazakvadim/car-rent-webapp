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
import com.kazak.carrent.service.UploadImageService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;
  private final CarClassRepository carClassRepository;
  private final CarBodyRepository carBodyRepository;
  private final CarBrandRepository carBrandRepository;
  private final CarTransmissionRepository carTransmissionRepository;
  private final CarMapper carMapper;
  private final UploadImageService uploadImageService;


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
  public void save(Car car, MultipartFile imageFile) {
    if (!imageFile.isEmpty()) {
      car.setImageName(uploadImageService.upload(imageFile));
    }
    carRepository.save(car);
  }

  @Override
  @Transactional
  public void update(Integer carId, CarPostDto carPostDto, MultipartFile imageFile) {
    if (!imageFile.isEmpty()) {
      carPostDto.setImageName(uploadImageService.upload(imageFile));
    }
    Car car = carRepository.getById(carId);
    carMapper.updateCarFromDto(carPostDto, car);
    carRepository.save(car);
  }

  @Override
  public List<Car> getFilteredCarList(List<CarBrand> carBrandsFiltered,
      List<String> carModelsFiltered,
      List<CarBody> carBodiesFiltered, List<CarClass> carClassesFiltered,
      List<CarTransmission> carTransmissionsFiltered) {
    List<String> carModelsTemp = new ArrayList<>();
    if (carBrandsFiltered == null) {
      carBrandsFiltered = carBrandRepository.findAll();
    }
    if (carModelsFiltered == null) {
      List<Car> cars = carRepository.findAll();
      for (Car car : cars) {
        carModelsTemp.add(car.getModel());
      }
    } else {
      carModelsTemp = carModelsFiltered;
    }
    if (carBodiesFiltered == null) {
      carBodiesFiltered = carBodyRepository.findAll();
    }
    if (carClassesFiltered == null) {
      carClassesFiltered = carClassRepository.findAll();
    }
    if (carTransmissionsFiltered == null) {
      carTransmissionsFiltered = carTransmissionRepository.findAll();
    }
    return carRepository
        .findByCarBrandInAndModelInAndCarBodyInAndCarClassInAndCarTransmissionIn(carBrandsFiltered,
            carModelsTemp, carBodiesFiltered, carClassesFiltered,
            carTransmissionsFiltered);
  }

}
