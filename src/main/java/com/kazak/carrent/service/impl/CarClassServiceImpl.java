package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.repository.CarClassRepository;
import com.kazak.carrent.service.CarClassService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarClassServiceImpl implements CarClassService {

  private final CarClassRepository carClassRepository;

  public CarClassServiceImpl(
      CarClassRepository carClassRepository) {
    this.carClassRepository = carClassRepository;
  }

  @Override
  public List<CarClass> getAll() {
    return carClassRepository.findAll();
  }

}
