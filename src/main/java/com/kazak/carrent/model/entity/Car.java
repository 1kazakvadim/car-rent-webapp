package com.kazak.carrent.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
  @JoinColumn(name = "car_transmission_id", nullable = false)
  private CarTransmission carTransmission;

  @ManyToOne
  @JoinColumn(name = "engine_type_id", nullable = false)
  private EngineType engineType;

  @Positive
  @Column(name = "engine_volume", nullable = false)
  private Double engineVolume;

  @Positive
  @Column(name = "number_of_seats", nullable = false)
  private Integer numberOfSeats;

  @Positive
  @Column(name = "fuel_consumption", nullable = false)
  private Double fuelConsumption;

  @Positive
  @Column(name = "rental_cost", nullable = false)
  private Double rentalCost;

  @Column(name = "image_name", nullable = false)
  private String imageName;

}
