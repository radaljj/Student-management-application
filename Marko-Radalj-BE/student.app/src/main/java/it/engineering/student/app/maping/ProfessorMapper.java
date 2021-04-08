package it.engineering.student.app.maping;

import it.engineering.student.app.dto.ProfessorDto;
import it.engineering.student.app.entity.Professor;
import org.mapstruct.Mapper;

@Mapper
public interface ProfessorMapper {

    ProfessorDto toProfessorDto(Professor professor);
    Professor toProfessor(ProfessorDto professorDto);
}
