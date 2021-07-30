package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarTransmission;
import java.util.List;

public interface CarTransmissionService {

  CarTransmission findByName(String carTransmissionName);

  List<CarTransmission> getAll();

}
