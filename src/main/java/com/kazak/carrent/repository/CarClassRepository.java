package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarClassRepository extends JpaRepository<CarClass, Integer> {

}
