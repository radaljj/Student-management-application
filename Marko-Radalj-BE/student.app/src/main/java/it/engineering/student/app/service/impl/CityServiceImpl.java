package it.engineering.student.app.service.impl;

import it.engineering.student.app.dto.CityDto;
import it.engineering.student.app.entity.City;
import it.engineering.student.app.maping.CityMapper;
import it.engineering.student.app.repository.CityRepository;
import it.engineering.student.app.service.CityService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {


    private final CityRepository cityRepository;
    private final CityMapper cityMapper= Mappers.getMapper(CityMapper.class);
@Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityDto> findAll() {
        List<City> entites = (List)cityRepository.findAll();
        return entites.stream().map(entity -> {
            return cityMapper.toCityDto(entity);
        }).collect(Collectors.toList());
    }
}
