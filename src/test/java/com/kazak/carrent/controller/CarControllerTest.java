package com.kazak.carrent.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import com.kazak.carrent.mock.MockCar;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

  @MockBean
  private UserService userService;

  @MockBean
  private CarService carService;

  @Mock
  private UserDetails currentUser;

  @Autowired
  private MockMvc mockMvc;

  private final Car car = MockCar.getMockCar();

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetCarDetail() throws Exception {
    when(carService.findById(car.getId())).thenReturn(car);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars/{carId}/detail", car.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(model().attribute("car", carService.findById(car.getId())))
        .andExpect(model().attributeExists("car"))
        .andExpect(MockMvcResultMatchers.view().name("car/car_detail"));

  }


}
