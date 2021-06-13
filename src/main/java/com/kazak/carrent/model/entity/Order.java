package com.kazak.carrent.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  private User user;

  private Car car;

  @Column(name = "date_of_issue", nullable = false)
  private LocalDate dateOfIssue;

  @Column(name = "date_of_return", nullable = false)
  private LocalDate dateOfReturn;

  @Column(name = "cancellation", nullable = false)
  private boolean isCancellation;

  @Column(name = "reason_of_cancellation", nullable = false)
  private String reasonOfCancellation;

  @Column(name = "rental_cost", nullable = false)
  private Double rentalCost;

}
