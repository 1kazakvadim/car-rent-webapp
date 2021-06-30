package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarBodyRepository extends JpaRepository<CarBody, Integer> {

}
