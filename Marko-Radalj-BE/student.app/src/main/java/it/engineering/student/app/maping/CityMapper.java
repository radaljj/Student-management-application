package it.engineering.student.app.maping;

import it.engineering.student.app.dto.CityDto;
import it.engineering.student.app.entity.City;
import org.mapstruct.Mapper;

@Mapper
public interface CityMapper {

    CityDto toCityDto(City city);
    City toCity(CityDto cityDto);
}
