package com.kazak.carrent.mapper;

import com.kazak.carrent.dto.PassportDataPostDto;
import com.kazak.carrent.model.entity.PassportData;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring"
)
public interface PassportMapper {

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updatePassportDataFromDto(
      PassportDataPostDto passportDataPostDto, @MappingTarget PassportData passportData);

}
