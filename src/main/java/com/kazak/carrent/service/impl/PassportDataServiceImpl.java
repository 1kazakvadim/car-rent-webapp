package com.kazak.carrent.service.impl;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.mapper.MapStructMapper;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.repository.PassportDataRepository;
import com.kazak.carrent.service.PassportDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PassportDataServiceImpl implements PassportDataService {

  private final PassportDataRepository passportDataRepository;
  private final MapStructMapper mapStructMapper;

  public PassportDataServiceImpl(
      PassportDataRepository passportDataRepository,
      MapStructMapper mapStructMapper) {
    this.passportDataRepository = passportDataRepository;
    this.mapStructMapper = mapStructMapper;
  }

  @Override
  public PassportData findById(Integer id) {
    return passportDataRepository.getById(id);
  }

  @Override
  public boolean isPassportNumberExists(String passportNumber) {
    return passportDataRepository.existsByPassportNumber(passportNumber);
  }

  @Override
  public boolean isPassportNumberExistsExceptPassportNumberWithId(String passportNumber,
      Integer id) {
    return passportDataRepository.existsByPassportNumberAndIdIsNot(passportNumber, id);
  }

  @Override
  public boolean isIdentificationNumberExists(String identificationNumber) {
    return passportDataRepository.existsByIdentificationNumber(identificationNumber);
  }

  @Override
  public boolean isIdentificationNumberExistsExceptIdentificationNumberWithId(
      String identificationNumber, Integer id) {
    return passportDataRepository.existsByIdentificationNumberAndIdIsNot(identificationNumber, id);
  }

  @Override
  @Transactional
  public void update(PassportDataPostDto passportDataPostDto) {
    PassportData passportData = passportDataRepository.getById(passportDataPostDto.getId());
    mapStructMapper.updatePassportDataFromDto(passportDataPostDto, passportData);
    passportDataRepository.save(passportData);
  }

}
