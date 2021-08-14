package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.CarOrderRepository;
import com.kazak.carrent.repository.CarRepository;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.service.CarOrderService;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarOrderServiceImpl implements CarOrderService {

  private static final String USER_ROLE = "USER";

  private final CarOrderRepository carOrderRepository;
  private final CarRepository carRepository;
  private final UserRepository userRepository;


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
    if (user.getUserRole().getName().equals(USER_ROLE)) {
      return carOrderRepository.findAllByUser(user);
    } else {
      return carOrderRepository.findAll();
    }
  }

  @Override
  public List<CarOrder> getAllByCarId(Integer carId) {
    return carOrderRepository.findAllByCar_Id(carId);
  }

  @Override
  public boolean checkIsCarAvailableByDate(Integer carId, LocalDate dateOfIssue,
      LocalDate dateOfReturn) {
    List<CarOrder> carOrders = carOrderRepository.findAllByCar_Id(carId);
    for (CarOrder carOrder : carOrders) {
      if (!carOrder.isCancellation()) {
        if (!carOrder.getDateOfIssue().isAfter(dateOfIssue) && !carOrder.getDateOfReturn()
            .isBefore(dateOfIssue) ||
            !carOrder.getDateOfIssue().isAfter(dateOfReturn) &&
                !carOrder.getDateOfReturn().isBefore(dateOfReturn)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean checkDate(LocalDate dateOfIssue, LocalDate dateOfReturn) {
    return !dateOfIssue.isBefore(LocalDate.now()) && !dateOfReturn.isBefore(LocalDate.now()) &&
        !dateOfReturn.isBefore(dateOfIssue);
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
