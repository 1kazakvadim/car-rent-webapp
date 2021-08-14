package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.repository.CarBrandRepository;
import com.kazak.carrent.service.CarBrandService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarBrandServiceImpl implements CarBrandService {

  private final CarBrandRepository carBrandRepository;


  @Override
  public CarBrand findByName(String carBrandName) {
    return carBrandRepository.findByName(carBrandName);
  }

  @Override
  public List<CarBrand> getAll() {
    return carBrandRepository.findAll();
  }

}
