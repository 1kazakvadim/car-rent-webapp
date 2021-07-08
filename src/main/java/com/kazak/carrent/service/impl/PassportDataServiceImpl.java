package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.repository.PassportDataRepository;
import com.kazak.carrent.service.PassportDataService;
import org.springframework.stereotype.Service;

@Service
public class PassportDataServiceImpl implements PassportDataService {

  private final PassportDataRepository passportDataRepository;

  public PassportDataServiceImpl(
      PassportDataRepository passportDataRepository) {
    this.passportDataRepository = passportDataRepository;
  }

  @Override
  public PassportData findById(Integer id) {
    return passportDataRepository.getById(id);
  }
}
