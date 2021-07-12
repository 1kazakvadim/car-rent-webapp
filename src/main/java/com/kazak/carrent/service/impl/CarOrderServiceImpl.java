package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.repository.CarOrderRepository;
import com.kazak.carrent.service.CarOrderService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CarOrderServiceImpl implements CarOrderService {

  private final CarOrderRepository carOrderRepository;

  public CarOrderServiceImpl(CarOrderRepository carOrderRepository) {
    this.carOrderRepository = carOrderRepository;
  }

  @Override
  public CarOrder findById(Integer id) {
    return carOrderRepository.getById(id);
  }

  @Override
  public List<CarOrder> getAll() {
    return carOrderRepository.findAll();
  }

}
