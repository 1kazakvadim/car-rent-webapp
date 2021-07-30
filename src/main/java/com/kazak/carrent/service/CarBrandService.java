package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarBrand;
import java.util.List;

public interface CarBrandService {

  CarBrand findByName(String carBrandName);

  List<CarBrand> getAll();

}
