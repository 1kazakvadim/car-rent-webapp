package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarClass;
import java.util.List;

public interface CarClassService {

  CarClass findByName(String carClassName);

  List<CarClass> getAll();

}
