package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.Car;
import com.kazak.carrent.model.entity.CarBody;
import com.kazak.carrent.model.entity.CarBrand;
import com.kazak.carrent.model.entity.CarClass;
import com.kazak.carrent.model.entity.CarTransmission;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

  List<Car> findByModel(String modelName);

  List<Car> findByCarBrandInAndModelInAndCarBodyInAndCarClassInAndCarTransmissionIn(
      List<CarBrand> carBrands, List<String> carModels, List<CarBody> carBodies,
      List<CarClass> carClasses, List<CarTransmission> carTransmission);

}
