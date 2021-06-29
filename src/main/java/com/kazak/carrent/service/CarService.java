package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.Car;
import java.util.List;

public interface CarService {

  List<Car> getAll();

  Car findById(Integer id);

}
