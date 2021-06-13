package com.kazak.carrent.model.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "car_brand_id", nullable = false)
  private CarBrand carBrand;

  @Column(name = "model", nullable = false)
  private String model;

  @Column(name = "color", nullable = false)
  private String color;

  @ManyToOne
  @JoinColumn(name = "car_body_id", nullable = false)
  private CarBody carBody;

  @ManyToOne
  @JoinColumn(name = "car_class_id", nullable = false)
  private CarClass carClass;

  @ManyToOne
  @Column(name = "car_transmission_id", nullable = false)
  private CarTransmission carTransmission;

  @ManyToOne
  @Column(name = "engine_type_id", nullable = false)
  private EngineType engineType;

  @Column(name = "engine_volume", nullable = false)
  private Double engineVolume;

  @Column(name = "number_of_seats", nullable = false)
  private Integer numberOfSeats;

  @Column(name = "release_date", nullable = false)
  private LocalDate releaseDate;

  @Column(name = "rental_cost", nullable = false)
  private Double rentalCost;

}
