package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.CarRepair;
import com.kazak.carrent.model.entity.User;
import com.kazak.carrent.repository.CarRepairRepository;
import com.kazak.carrent.repository.UserRepository;
import com.kazak.carrent.service.CarRepairService;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CarRepairServiceImpl implements CarRepairService {

  private final CarRepairRepository carRepairRepository;
  private final UserRepository userRepository;

  public CarRepairServiceImpl(CarRepairRepository carRepairRepository,
      UserRepository userRepository) {
    this.carRepairRepository = carRepairRepository;
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

}
