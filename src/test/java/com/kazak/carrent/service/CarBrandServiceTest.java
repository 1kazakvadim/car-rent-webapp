package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.mock.MockCarBrand;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CarBrandServiceTest {

  @MockBean
  private CarBrandService carBrandService;

  private final List<CarBrand> carBrands = MockCarBrand.getMockCarBrands();
  private final CarBrand carBrand = MockCarBrand.getMockCarBrand();

  @Test
  void findCarBrandByName_True() {
    when(carBrandService.findByName(carBrand.getName())).thenReturn(carBrand);
    assertThat(carBrandService.findByName(carBrand.getName())).isEqualTo(carBrand);
  }

  @Test
  void getAllCarBrands() {
    when(carBrandService.getAll()).thenReturn(carBrands);
    assertThat(carBrandService.getAll()).isEqualTo(carBrands);
    assertEquals(2, carBrands.size());
  }

}
