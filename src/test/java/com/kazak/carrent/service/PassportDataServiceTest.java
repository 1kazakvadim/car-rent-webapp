package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.model.entity.PassportData;
import com.kazak.carrent.mock.MockPassportData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PassportDataServiceTest {

  @MockBean
  private PassportDataService passportDataService;

  private final PassportData passportData = MockPassportData.getMockPassportData();

  @Test
  void findPassportDataById() {
    when(passportDataService.findById(passportData.getId())).thenReturn(passportData);
    assertThat(passportDataService.findById(passportData.getId())).isEqualTo(passportData);
  }

  @Test
  void isPassportNumberExists_True() {
    when(passportDataService.isPassportNumberExists(passportData.getPassportNumber()))
        .thenReturn(true);
    assertThat(passportDataService.isPassportNumberExists(passportData.getPassportNumber()))
        .isTrue();
  }

  @Test
  void isPassportNumberExists_False() {
    when(passportDataService.isPassportNumberExists("0"))
        .thenReturn(false);
    assertThat(passportDataService.isPassportNumberExists("0"))
        .isFalse();
  }

  @Test
  void isIdentificationNumberExists_True() {
    when(passportDataService.isIdentificationNumberExists(passportData.getIdentificationNumber()))
        .thenReturn(true);
    assertThat(
        passportDataService.isIdentificationNumberExists(passportData.getIdentificationNumber()))
        .isTrue();
  }

  @Test
  void isIdentificationNumberExists_False() {
    when(passportDataService.isIdentificationNumberExists("0"))
        .thenReturn(false);
    assertThat(
        passportDataService.isIdentificationNumberExists("0"))
        .isFalse();
  }

  @Test
  void updatePassportData() {
    PassportDataPostDto passportDataPostDto = new PassportDataPostDto();
    passportDataService.update(passportDataPostDto, 1);
    verify(passportDataService, times(1)).update(passportDataPostDto, 1);
  }

}
