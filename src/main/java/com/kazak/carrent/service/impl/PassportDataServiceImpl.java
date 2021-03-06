package com.kazak.carrent.service.impl;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.mapper.PassportMapper;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.repository.PassportDataRepository;
import com.kazak.carrent.service.PassportDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PassportDataServiceImpl implements PassportDataService {

  private final PassportDataRepository passportDataRepository;
  private final PassportMapper passportMapper;


  @Override
  public PassportData findById(Integer id) {
    return passportDataRepository.getById(id);
  }

  @Override
  public boolean isPassportNumberExists(String passportNumber) {
    return passportDataRepository.existsByPassportNumber(passportNumber);
  }

  @Override
  public boolean isPassportNumberExists(String passportNumber,
      Integer id) {
    return passportDataRepository.existsByPassportNumberAndIdIsNot(passportNumber, id);
  }

  @Override
  public boolean isIdentificationNumberExists(String identificationNumber) {
    return passportDataRepository.existsByIdentificationNumber(identificationNumber);
  }

  @Override
  public boolean isIdentificationNumberExists(
      String identificationNumber, Integer id) {
    return passportDataRepository.existsByIdentificationNumberAndIdIsNot(identificationNumber, id);
  }

  @Override
  @Transactional
  public void update(PassportDataPostDto passportDataPostDto, Integer passportId) {
    PassportData passportData = passportDataRepository.getById(passportId);
    passportMapper.updatePassportDataFromDto(passportDataPostDto, passportData);
    passportDataRepository.save(passportData);
  }

}
