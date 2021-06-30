package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.repository.CarBrandRepository;
import com.kazak.carrent.service.CarBrandService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl implements CarBrandService {

  private final CarBrandRepository carBrandRepository;

  public CarBrandServiceImpl(CarBrandRepository carBrandRepository) {
    this.carBrandRepository = carBrandRepository;
  }

  @Override
  public List<CarBrand> getAll() {
    return carBrandRepository.findAll();
  }
}
