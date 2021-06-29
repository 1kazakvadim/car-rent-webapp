package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.repository.CarRepository;
import com.kazak.carrent.service.CarService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  private final CarRepository carRepository;

  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public List<Car> getAll() {
    return carRepository.findAll();
  }

  @Override
  public Car findById(Integer id) {
    return carRepository.findById(id).orElse(null);
  }

}
