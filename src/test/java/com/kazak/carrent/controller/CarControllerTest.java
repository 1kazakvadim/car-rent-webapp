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
import com.kazak.carrent.mock.MockEngineType;
import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
import com.kazak.carrent.service.CarBodyService;
import com.kazak.carrent.service.CarBrandService;
import com.kazak.carrent.service.CarClassService;
import com.kazak.carrent.service.CarService;
import com.kazak.carrent.service.CarTransmissionService;
import com.kazak.carrent.service.EngineTypeService;
import com.kazak.carrent.service.UserService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

  private final Car car = MockCar.getMockCar();
  private final List<CarBrand> carBrands = MockCarBrand.getMockCarBrands();
  private final List<CarBody> carBodies = MockCarBody.getMockCarBodies();
  private final List<CarClass> carClasses = MockCarClass.getMockCarClasses();
  private final List<CarTransmission> carTransmissions = MockCarTransmission
      .getMockCarTransmissions();
  private final List<EngineType> engineTypes = MockEngineType.getMockEngineTypes();

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

  @MockBean
  private EngineTypeService engineTypeService;

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;


  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetCarDetail() throws Exception {
    when(carService.findById(car.getId())).thenReturn(car);
    assertThat(carService.findById(car.getId())).isEqualTo(car);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars/{carId}/detail", car.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("car", carService.findById(car.getId())))
        .andExpect(MockMvcResultMatchers.view().name("car/car_detail"));
  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetEditCar() throws Exception {
    when(carBrandService.getAll()).thenReturn(carBrands);
    assertThat(carBrandService.getAll()).isEqualTo(carBrands);
    assertEquals(2, carBrands.size());
    when(carBodyService.getAll()).thenReturn(carBodies);
    assertThat(carBodyService.getAll()).isEqualTo(carBodies);
    assertEquals(2, carBodies.size());
    when(carClassService.getAll()).thenReturn(carClasses);
    assertThat(carClassService.getAll()).isEqualTo(carClasses);
    assertEquals(2, carClasses.size());
    when(carTransmissionService.getAll()).thenReturn(carTransmissions);
    assertThat(carTransmissionService.getAll()).isEqualTo(carTransmissions);
    assertEquals(2, carTransmissions.size());
    when(engineTypeService.getAll()).thenReturn(engineTypes);
    assertThat(engineTypeService.getAll()).isEqualTo(engineTypes);
    assertEquals(2, engineTypes.size());
    when(carService.findById(car.getId())).thenReturn(car);
    assertThat(carService.findById(car.getId())).isEqualTo(car);
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars/{carId}/edit", car.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carBrands", carBrandService.getAll()))
        .andExpect(model().attribute("carBodies", carBodyService.getAll()))
        .andExpect(model().attribute("carClasses", carClassService.getAll()))
        .andExpect(model().attribute("carTransmissions", carTransmissionService.getAll()))
        .andExpect(model().attribute("engineTypes", engineTypeService.getAll()))
        .andExpect(model().attribute("car", carService.findById(car.getId())))
        .andExpect(MockMvcResultMatchers.view().name("car/car_edit"));
  }

//  @WithMockUser(roles = {"ADMIN", "MANAGER"})
//  @Test
//  void shouldSaveEditCar() throws Exception {
//    CarPostDto carPostDto = new CarPostDto();
//    carPostDto.setId(car.getId());
//    carPostDto.setCarBrand(car.getCarBrand());
//    carPostDto.setModel(car.getModel());
//    carPostDto.setColor(car.getColor());
//    carPostDto.setCarBody(car.getCarBody());
//    carPostDto.setCarClass(car.getCarClass());
//    carPostDto.setCarTransmission(car.getCarTransmission());
//    carPostDto.setEngineType(car.getEngineType());
//    carPostDto.setEngineVolume(car.getEngineVolume());
//    carPostDto.setNumberOfSeats(car.getNumberOfSeats());
//    carPostDto.setFuelConsumption(car.getFuelConsumption());
//    carPostDto.setRentalCost(car.getRentalCost());
//    carPostDto.setImageName(car.getImageName());
//    MockMultipartFile imageFile = new MockMultipartFile("image", "image.jpg",
//        MediaType.IMAGE_JPEG_VALUE, "car".getBytes());
//
//    this.mockMvc.perform(
//        MockMvcRequestBuilders.multipart("/profile/cars/{carId}/edit", car.getId())
//        .accept(MediaType.MULTIPART_FORM_DATA_VALUE)
//        .requestAttr("car", carPostDto)
//        .requestAttr("imageFile", imageFile)
////            .requestAttr("car", carPostDto)
//    ).andExpect(status().is3xxRedirection())
//        .andExpect(redirectedUrl("/profile/cars/{carId}/edit"));
//  }

  @WithMockUser(roles = {"ADMIN", "MANAGER"})
  @Test
  void shouldGetAddNewCar() throws Exception {
    when(carBrandService.getAll()).thenReturn(carBrands);
    assertThat(carBrandService.getAll()).isEqualTo(carBrands);
    assertEquals(2, carBrands.size());
    when(carBodyService.getAll()).thenReturn(carBodies);
    assertThat(carBodyService.getAll()).isEqualTo(carBodies);
    assertEquals(2, carBodies.size());
    when(carClassService.getAll()).thenReturn(carClasses);
    assertThat(carClassService.getAll()).isEqualTo(carClasses);
    assertEquals(2, carClasses.size());
    when(carTransmissionService.getAll()).thenReturn(carTransmissions);
    assertThat(carTransmissionService.getAll()).isEqualTo(carTransmissions);
    assertEquals(2, carTransmissions.size());
    when(engineTypeService.getAll()).thenReturn(engineTypes);
    assertThat(engineTypeService.getAll()).isEqualTo(engineTypes);
    assertEquals(2, engineTypes.size());
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/profile/cars/new", car.getId()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(model().attribute("carBrands", carBrandService.getAll()))
        .andExpect(model().attribute("carBodies", carBodyService.getAll()))
        .andExpect(model().attribute("carClasses", carClassService.getAll()))
        .andExpect(model().attribute("carTransmissions", carTransmissionService.getAll()))
        .andExpect(model().attribute("engineTypes", engineTypeService.getAll()))
        .andExpect(model().attribute("car", new Car()))
        .andExpect(MockMvcResultMatchers.view().name("car/car_new"));
  }

}
