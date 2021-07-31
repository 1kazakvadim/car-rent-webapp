package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarOrder;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface CarOrderService {

  CarOrder findById(Integer id);

  List<CarOrder> getAll();

  List<CarOrder> getAll(UserDetails currentUser);

  CarOrder save(CarOrder carOrder, Integer carDetailId, UserDetails currentUser);

  void cancelCarOrder (String reasonOfCancellation, Integer carOrderId);

}
