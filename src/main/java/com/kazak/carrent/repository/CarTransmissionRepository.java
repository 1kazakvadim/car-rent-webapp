package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.CarTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTransmissionRepository extends JpaRepository<CarTransmission, Integer> {

}
