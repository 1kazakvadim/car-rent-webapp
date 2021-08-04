package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.CarOrderRepository;
import com.kazak.carrent.repository.CarRepository;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.service.CarOrderService;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarOrderServiceImpl implements CarOrderService {

  private final CarOrderRepository carOrderRepository;
  private final CarRepository carRepository;
  private final UserRepository userRepository;

  public CarOrderServiceImpl(CarOrderRepository carOrderRepository,
      CarRepository carRepository, UserRepository userRepository) {
    this.carOrderRepository = carOrderRepository;
    this.carRepository = carRepository;
    this.userRepository = userRepository;
  }

  @Override
  public CarOrder findById(Integer id) {
    return carOrderRepository.getById(id);
  }

  @Override
  public List<CarOrder> getAll() {
    return carOrderRepository.findAll();
  }

  @Override
  public List<CarOrder> getAll(UserDetails currentUser) {
    User user = userRepository.findByUsername(currentUser.getUsername());
    if (user.getUserRole().getName().equals("USER")) {
      return carOrderRepository.findAllByUser(user);
    } else {
      return carOrderRepository.findAll();
    }
  }

  @Override
  @Transactional
  public void save(CarOrder carOrder, Integer carDetailId, UserDetails currentUser) {
    Car car = carRepository.getById(carDetailId);
    carOrder.setUser(userRepository.findByUsername(currentUser.getUsername()));
    carOrder.setCar(car);
    carOrder.setTotalCost(Math.abs(car.getRentalCost() * carOrder.getDateOfIssue()
        .until(carOrder.getDateOfReturn(), ChronoUnit.DAYS)));
    carOrderRepository.save(carOrder);
  }

  @Override
  @Transactional
  public void cancelCarOrder(String reasonOfCancellation, Integer carOrderId) {
    CarOrder carOrder = carOrderRepository.getById(carOrderId);
    carOrder.setReasonOfCancellation(reasonOfCancellation);
    carOrder.setCancellation(true);
    carOrderRepository.save(carOrder);
  }


}
