package com.kazak.carrent.dto;

import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CarPostDto {

  private Integer id;

  private CarBrand carBrand;

  private String model;

  private String color;

  private CarBody carBody;

  private CarClass carClass;

  private CarTransmission carTransmission;

  private EngineType engineType;

  @Positive
  private Double engineVolume;

  @Positive
  private Integer numberOfSeats;

  @Positive
  private Double fuelConsumption;

  @Positive
  private Double rentalCost;

  private String imageName;

}
