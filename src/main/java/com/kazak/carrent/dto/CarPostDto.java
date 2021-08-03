package com.kazak.carrent.dto;

import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import com.kazak.carrent.model.entity.EngineType;
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

  private Double engineVolume;

  private Integer numberOfSeats;

  private Double fuelConsumption;

  private Double rentalCost;

  private String imageName;

}
