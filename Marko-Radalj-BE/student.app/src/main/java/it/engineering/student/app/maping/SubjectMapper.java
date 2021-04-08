package it.engineering.student.app.maping;

import it.engineering.student.app.dto.SubjectDto;
import it.engineering.student.app.entity.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper {

    SubjectDto toSubjectDto(Subject subject);
    Subject toSubject(SubjectDto studentDto);
}
