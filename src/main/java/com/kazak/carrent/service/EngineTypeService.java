package com.kazak.carrent.service;

import com.kazak.carrent.model.entity.EngineType;
import java.util.List;

public interface EngineTypeService {

  EngineType findByName(String engineTypeName);

  List<EngineType> getAll();

}
