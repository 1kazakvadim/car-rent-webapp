package com.kazak.carrent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockCar;
import com.kazak.carrent.mock.MockCarBody;
import com.kazak.carrent.mock.MockCarBrand;
import com.kazak.carrent.mock.MockCarClass;
import com.kazak.carrent.mock.MockCarTransmission;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogControllerTest {

  private final Car car = MockCar.getMockCar();
  private final List<Car> cars = MockCar.getMockCars();
  private final List<CarBrand> carBrands = MockCarBrand.getMockCarBrands();
  private final List<CarBody> carBodies = MockCarBody.getMockCarBodies();
  private final List<CarClass> carClasses = MockCarClass.getMockCarClasses();
  private final List<CarTransmission> carTransmissions = MockCarTransmission
      .getMockCarTransmissions();

  @MockBean
  private CarService carService;

  @MockBean
  private CarBrandService carBrandService;

  @MockBean
  private CarBodyService carBodyService;

  @MockBean
  private CarClassService carClassService;

  @MockBean
  private CarTransmissionService carTransmissionService;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldGetCatalogPage() throws Exception {
    List<String> models = new ArrayList<>();
    when(carBrandService.getAll()).thenReturn(carBrands);
    assertThat(carBrandService.getAll()).isEqualTo(carBrands);
    assertEquals(2, carBrands.size());
    for (Car car : cars) {
      models.add(car.getModel());
    }
    assertEquals(2, models.size());
    when(carBodyService.getAll()).thenReturn(carBodies);
    assertThat(carBodyService.getAll()).isEqualTo(carBodies);
    assertEquals(2, carBodies.size());
    when(carClassService.getAll()).thenReturn(carClasses);
    assertThat(carClassService.getAll()).isEqualTo(carClasses);
    assertEquals(2, carClasses.size());
    when(carTransmissionService.getAll()).thenReturn(carTransmissions);
    assertThat(carTransmissionService.getAll()).isEqualTo(carTransmissions);
    assertEquals(2, carTransmissions.size());
    when(carService.getAll()).thenReturn(cars);
    assertThat(carService.getAll()).isEqualTo(cars);
    assertEquals(2, cars.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/catalog"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carBrands", carBrandService.getAll()))
        .andExpect(model().attribute("carBodies", carBodyService.getAll()))
        .andExpect(model().attribute("carClasses", carClassService.getAll()))
        .andExpect(model().attribute("carTransmissions", carTransmissionService.getAll()))
        .andExpect(model().attribute("models", models))
        .andExpect(model().attribute("cars",cars))
        .andExpect(MockMvcResultMatchers.view().name("catalog/catalog"));
  }

  @Test
  void shouldGetCarDetail() throws Exception {
    when(carService.findById(car.getId())).thenReturn(car);
    assertThat(carService.findById(car.getId())).isEqualTo(car);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/catalog/{carId}/detail", car.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("car", carService.findById(car.getId())))
        .andExpect(MockMvcResultMatchers.view().name("catalog/catalog_detail"));
  }

}
