package com.kazak.carrent.service.impl;

import com.kazak.carrent.model.entity.EngineType;
import com.kazak.carrent.repository.EngineTypeRepository;
import com.kazak.carrent.service.EngineTypeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EngineTypeServiceImpl implements EngineTypeService {

  private final EngineTypeRepository engineTypeRepository;

  public EngineTypeServiceImpl(
      EngineTypeRepository engineTypeRepository) {
    this.engineTypeRepository = engineTypeRepository;
  }

  @Override
  public EngineType findByName(String engineTypeName) {
    return engineTypeRepository.findByName(engineTypeName);
  }

  @Override
  public List<EngineType> getAll() {
    return engineTypeRepository.findAll();
  }
}
