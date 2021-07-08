package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.PassportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportDataRepository extends JpaRepository<PassportData, Integer> {

  boolean existsByPassportNumber(String passportNumber);

  boolean existsByIdentificationNumber(String identificationNumber);

}
