package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarOrder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface CarOrderService {

  CarOrder findById(Integer id);

  List<CarOrder> getAll();

  List<CarOrder> getAll(UserDetails currentUser);

  List<CarOrder> getAllByCarId(Integer carId);

  boolean checkIsCarAvailableByDate(Integer carId, LocalDate dateOfIssue, LocalDate dateOfReturn);

  void save(CarOrder carOrder, Integer carDetailId, UserDetails currentUser);

  void cancelCarOrder (String reasonOfCancellation, Integer carOrderId);

}
