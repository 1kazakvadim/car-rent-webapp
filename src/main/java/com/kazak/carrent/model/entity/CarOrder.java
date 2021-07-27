package com.kazak.carrent.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_order")
public class CarOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_id", nullable = false)
  @PrimaryKeyJoinColumn
  private Car car;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date_of_issue", nullable = false)
  private LocalDate dateOfIssue;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "date_of_return", nullable = false)
  private LocalDate dateOfReturn;

  @ColumnDefault("false")
  @Column(name = "cancellation", nullable = false)
  private boolean isCancellation;

  @Column(name = "reason_of_cancellation")
  private String reasonOfCancellation;

  @Column(name = "rental_cost", nullable = false)
  private Double rentalCost;

}