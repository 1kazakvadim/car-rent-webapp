package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.CarRepair;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public interface CarRepairService {

  CarRepair findById(Integer id);

  List<CarRepair> getAll();

  List<CarRepair> getAll(UserDetails currentUser);

  void save(Integer orderId, String damageInformation, Double repairCost);

}
