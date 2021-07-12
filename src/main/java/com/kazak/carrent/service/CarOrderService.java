package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarOrder;
import java.util.List;

public interface CarOrderService {

  CarOrder findById(Integer id);

  List<CarOrder> getAll();

}
