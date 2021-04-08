package it.engineering.student.app.service;
import it.engineering.student.app.dto.ProfessorDto;
import it.engineering.student.app.dto.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {


    List<ProfessorDto> findAll();

    void deleteById(Long id);

    ProfessorDto update(ProfessorDto professorDto) throws  Exception;


    ProfessorDto save(ProfessorDto professorDto) throws Exception;

    Optional<ProfessorDto> findById(Long id);

    Page<ProfessorDto> findByPage(Pageable pageable);

}
