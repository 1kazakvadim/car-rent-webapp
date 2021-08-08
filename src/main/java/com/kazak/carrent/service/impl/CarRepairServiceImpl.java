package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.CarOrderRepository;
import com.kazak.carrent.repository.CarRepairRepository;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.service.CarRepairService;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarRepairServiceImpl implements CarRepairService {

  private final CarRepairRepository carRepairRepository;
  private final CarOrderRepository carOrderRepository;
  private final UserRepository userRepository;

  public CarRepairServiceImpl(CarRepairRepository carRepairRepository,
      CarOrderRepository carOrderRepository,
      UserRepository userRepository) {
    this.carRepairRepository = carRepairRepository;
    this.carOrderRepository = carOrderRepository;
    this.userRepository = userRepository;
  }

  @Override
  public CarRepair findById(Integer id) {
    return carRepairRepository.getById(id);
  }

  @Override
  public List<CarRepair> getAll() {
    return carRepairRepository.findAll();
  }

  @Override
  public List<CarRepair> getAll(UserDetails currentUser) {
    User user = userRepository.findByUsername(currentUser.getUsername());
    if (user.getUserRole().getName().equals("USER")) {
      return carRepairRepository.findAllByCarOrder_User(user);
    } else {
      return carRepairRepository.findAll();
    }
  }

  @Override
  @Transactional
  public void save(Integer orderId, String damageInformation, Double repairCost) {
    CarRepair carRepair = new CarRepair();
    carRepair.setCarOrder(carOrderRepository.getById(orderId));
    carRepair.setDamageInformation(damageInformation);
    carRepair.setRepairCost(repairCost);
    carRepairRepository.save(carRepair);
  }

}
