package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kazak.carrent.dto.CarPostDto;
import com.kazak.carrent.model.entity.Car;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
class CarServiceTest {

  @MockBean
  private CarService carService;

  private List<Car> cars;
  private Car car;

  @BeforeEach
  void init() {
    cars = new ArrayList<>();
    car = Car.builder()
        .id(1)
        .model("model")
        .build();
    cars.add(car);
  }

  @Test
  void findCarById() {
    when(carService.findById(car.getId())).thenReturn(car);
    assertThat(carService.findById(car.getId())).isEqualTo(car);
  }

  @Test
  void findCarByModel() {
    when(carService.findByModel(car.getModel())).thenReturn(cars);
    assertThat(carService.findByModel(car.getModel())).isEqualTo(cars);
    assertEquals(1, cars.size());
  }

  @Test
  void getAllCars() {
    when(carService.getAll()).thenReturn(cars);
    assertThat(carService.getAll()).isEqualTo(cars);
    assertEquals(1, cars.size());
  }

  @Test
  void saveCar() {
    MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg",
        MediaType.IMAGE_JPEG_VALUE, "car".getBytes());
    carService.save(car, imageFile);
    verify(carService, times(1)).save(car, imageFile);
  }

  @Test
  void updateCar() {
    MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg",
        MediaType.IMAGE_JPEG_VALUE, "car".getBytes());
    CarPostDto carPostDto = new CarPostDto();
    carService.update(1, carPostDto, imageFile);
    verify(carService, times(1)).update(1, carPostDto, imageFile);
  }

}
