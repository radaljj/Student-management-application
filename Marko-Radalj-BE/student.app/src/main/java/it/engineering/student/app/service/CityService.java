package it.engineering.student.app.service;

import it.engineering.student.app.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();
}
