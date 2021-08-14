package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.repository.CarBodyRepository;
import com.kazak.carrent.service.CarBodyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarBodyServiceImpl implements CarBodyService {

  private final CarBodyRepository carBodyRepository;


  @Override
  public CarBody findByName(String carBodyName) {
    return carBodyRepository.findByName(carBodyName);
  }

  @Override
  public List<CarBody> getAll() {
    return carBodyRepository.findAll();
  }

}
