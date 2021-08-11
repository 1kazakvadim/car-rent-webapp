package com.kazak.carrent.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarOrder;
import com.kazak.carrent.model.entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class CarOrderServiceTest {

  @MockBean
  private CarOrderService carOrderService;

  @MockBean
  private UserDetails currentUser;

  private List<CarOrder> carOrders;
  private CarOrder carOrder;

  @BeforeEach
  void init() {
    carOrders = new ArrayList<>();
    carOrder = CarOrder.builder()
        .id(1)
        .user(User.builder().id(1).username(currentUser.getUsername()).build())
        .car(Car.builder().id(1).build())
        .dateOfIssue(LocalDate.now())
        .dateOfReturn(LocalDate.now().plusDays(1))
        .isCancellation(false)
        .reasonOfCancellation("")
        .totalCost(1000D)
        .build();
    carOrders.add(carOrder);
  }

  @Test
  void getCarOrderById() {
    when(carOrderService.findById(carOrder.getId())).thenReturn(carOrder);
    assertThat(carOrderService.findById(carOrder.getId())).isEqualTo(carOrder);
  }

  @Test
  void getAllCarOrders() {
    when(carOrderService.getAll()).thenReturn(carOrders);
    assertEquals(1, carOrders.size());
  }

  @Test
  void getAllCarOrdersByUsername() {
    when(currentUser.getUsername()).thenReturn("username");
    when(carOrderService.getAll(currentUser)).thenReturn(carOrders);
    assertThat(currentUser.getUsername()).isEqualTo("username");
    assertThat(carOrderService.getAll(currentUser)).isEqualTo(carOrders);
    assertEquals(1, carOrders.size());
  }

  @Test
  void checkIsCarAvailableByDate_False() {
    LocalDate dateOfIssue = LocalDate.now();
    LocalDate dateOfReturn = LocalDate.now().plusDays(1);
    when(carOrderService.getAllByCarId(1)).thenReturn(carOrders);
    assertThat(carOrderService.getAllByCarId(1)).isEqualTo(carOrders);
    assertThat(!carOrder.isCancellation()).isTrue();
    assertThat(!carOrder.getDateOfIssue().isAfter(dateOfIssue) && !carOrder.getDateOfReturn()
        .isBefore(dateOfIssue) ||
        !carOrder.getDateOfIssue().isAfter(dateOfReturn) &&
            !carOrder.getDateOfReturn().isBefore(dateOfReturn)).isTrue();
  }

  @Test
  void checkIsCarAvailableByDate_True() {
    LocalDate dateOfIssue = LocalDate.now().plusDays(2);
    LocalDate dateOfReturn = LocalDate.now().plusDays(3);
    when(carOrderService.getAllByCarId(1)).thenReturn(carOrders);
    assertThat(carOrderService.getAllByCarId(1)).isEqualTo(carOrders);
    assertThat(!carOrder.isCancellation()).isTrue();
    assertThat(!carOrder.getDateOfIssue().isAfter(dateOfIssue) && !carOrder.getDateOfReturn()
        .isBefore(dateOfIssue) ||
        !carOrder.getDateOfIssue().isAfter(dateOfReturn) &&
            !carOrder.getDateOfReturn().isBefore(dateOfReturn)).isFalse();
  }

  @Test
  void checkDate_True() {
    LocalDate dateOfIssue = LocalDate.now();
    LocalDate dateOfReturn = LocalDate.now().plusDays(1);
    assertThat(!dateOfIssue.isBefore(LocalDate.now()) && !dateOfReturn.isBefore(LocalDate.now()) &&
        !dateOfReturn.isBefore(dateOfIssue)).isTrue();
  }

  @Test
  void checkDate_False() {
    LocalDate dateOfIssue = LocalDate.now().plusDays(1);
    LocalDate dateOfReturn = LocalDate.now();
    assertThat(!dateOfIssue.isBefore(LocalDate.now()) && !dateOfReturn.isBefore(LocalDate.now()) &&
        !dateOfReturn.isBefore(dateOfIssue)).isFalse();
  }

  @Test
  void saveCarOrder() {
    carOrderService.save(carOrder, 1, currentUser);
    verify(carOrderService, times(1)).save(carOrder, 1, currentUser);
  }


}