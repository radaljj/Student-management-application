package it.engineering.student.app.service.impl;

import it.engineering.student.app.dto.ProfessorDto;
import it.engineering.student.app.entity.City;
import it.engineering.student.app.entity.Professor;
import it.engineering.student.app.entity.Title;
import it.engineering.student.app.maping.ProfessorMapper;
import it.engineering.student.app.repository.CityRepository;
import it.engineering.student.app.repository.ProfessorRepository;
import it.engineering.student.app.repository.TitleRepository;
import it.engineering.student.app.service.ProfessorService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final CityRepository cityRepository;
    private final TitleRepository titleRepository;
    private final ProfessorMapper professorMapper= Mappers.getMapper(ProfessorMapper.class);

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository professorRepository, CityRepository cityRepository, TitleRepository titleRepository) {
        this.professorRepository = professorRepository;
        this.cityRepository = cityRepository;
        this.titleRepository = titleRepository;
    }

    @Override
    public List<ProfessorDto> findAll() {
        List<Professor> entites = (List)professorRepository.findAll();
        return entites.stream().map(entity -> {
            return professorMapper.toProfessorDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
professorRepository.deleteById(id);
    }

    @Override
    public ProfessorDto update(ProfessorDto professorDto) throws Exception {

        Optional<Professor> entity = professorRepository.findById(professorDto.getProfessorId());
        if (!entity.isPresent()) {
            throw new Exception( "Professor does not exist!");
        }
        Professor p = professorRepository.save(professorMapper.toProfessor(professorDto));
        Optional<City> city = cityRepository.findById(professorDto.getCity().getCityId());
        Optional<Title> title = titleRepository.findById(professorDto.getTitle().getTitleId());

        if (city.isPresent() && title.isPresent()){
            p.setCity(city.get());
            p.setTitle(title.get());
        }
        return professorMapper.toProfessorDto(professorRepository.save(p));
    }

    @Override
    public ProfessorDto save(ProfessorDto professorDto) throws Exception {
        Optional<Professor> professor = professorRepository.findById(professorDto.getProfessorId());
        if (professor.isPresent()){
            throw new Exception( "Professor already exists!");
        }
        Professor p = professorMapper.toProfessor(professorDto);
        Optional<City> city = cityRepository.findById(professorDto.getCity().getCityId());
        Optional<Title> title = titleRepository.findById(professorDto.getTitle().getTitleId());

        if (city.isPresent()&&title.isPresent()){
            p.setCity(city.get());
            p.setTitle(title.get());
        }
        return professorMapper.toProfessorDto(professorRepository.save(p));
    }

    @Override
    public Optional<ProfessorDto> findById(Long id) {
        Optional<Professor> professor = professorRepository.findById(id);
        if (professor.isPresent()) {
            return Optional.of(professorMapper.toProfessorDto(professor.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<ProfessorDto> findByPage(Pageable pageable) {
        Page<ProfessorDto> entites = professorRepository.findAll(pageable).map(professorMapper::toProfessorDto);
        return entites;

    }
}
