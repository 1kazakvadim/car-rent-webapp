package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.repository.CarBodyRepository;
import com.kazak.carrent.service.CarBodyService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarBodyServiceImpl implements CarBodyService {

  private final CarBodyRepository carBodyRepository;

  public CarBodyServiceImpl(
      CarBodyRepository carBodyRepository) {
    this.carBodyRepository = carBodyRepository;
  }

  @Override
  public CarBody findByName(String carBodyName) {
    return carBodyRepository.findByName(carBodyName);
  }

  @Override
  public List<CarBody> getAll() {
    return carBodyRepository.findAll();
  }

}
