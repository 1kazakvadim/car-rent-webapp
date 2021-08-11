package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarBrand;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarBrandServiceTest {

  @MockBean
  private CarBrandService carBrandService;

  private List<CarBrand> carBrands;
  private CarBrand carBrand;

  @BeforeEach
  void init() {
    carBrands = new ArrayList<>();
    carBrand = new CarBrand();
    carBrand.setName("name");
    carBrands.add(carBrand);
  }

  @Test
  void findCarBrandByName_True() {
    when(carBrandService.findByName(carBrand.getName())).thenReturn(carBrand);
    assertThat(carBrandService.findByName(carBrand.getName())).isEqualTo(carBrand);
  }

  @Test
  void getAllCarBrands() {
    when(carBrandService.getAll()).thenReturn(carBrands);
    assertThat(carBrandService.getAll()).isEqualTo(carBrands);
    assertEquals(1, carBrands.size());
  }

}
