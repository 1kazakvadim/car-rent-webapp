package com.kazak.carrent.repository;

import com.kazak.carrent.model.entity.EngineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EngineTypeRepository extends JpaRepository<EngineType, Integer> {

  EngineType findByName(String engineTypeName);

}
