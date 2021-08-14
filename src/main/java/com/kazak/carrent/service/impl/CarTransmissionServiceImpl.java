package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.repository.CarTransmissionRepository;
import com.kazak.carrent.service.CarTransmissionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarTransmissionServiceImpl implements CarTransmissionService {

  private final CarTransmissionRepository carTransmissionRepository;


  @Override
  public CarTransmission findByName(String carTransmissionName) {
    return carTransmissionRepository.findByName(carTransmissionName);
  }

  @Override
  public List<CarTransmission> getAll() {
    return carTransmissionRepository.findAll();
  }

}
