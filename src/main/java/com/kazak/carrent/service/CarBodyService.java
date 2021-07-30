package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarBody;
import java.util.List;

public interface CarBodyService {

  CarBody findByName(String carBodyName);

  List<CarBody> getAll();

}
