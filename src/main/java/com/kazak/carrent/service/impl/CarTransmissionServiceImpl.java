package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.repository.CarTransmissionRepository;
import com.kazak.carrent.service.CarTransmissionService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarTransmissionServiceImpl implements CarTransmissionService {

  private final CarTransmissionRepository carTransmissionRepository;

  public CarTransmissionServiceImpl(
      CarTransmissionRepository carTransmissionRepository) {
    this.carTransmissionRepository = carTransmissionRepository;
  }

  @Override
  public List<CarTransmission> getAll() {
    return carTransmissionRepository.findAll();
  }
}
