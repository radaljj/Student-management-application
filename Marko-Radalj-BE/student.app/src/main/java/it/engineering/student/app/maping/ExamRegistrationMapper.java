package it.engineering.student.app.maping;

import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.dto.ExamRegistrationDto;

import it.engineering.student.app.entity.ExamRegistration;
import org.mapstruct.Mapper;

@Mapper
public interface ExamRegistrationMapper {

    ExamRegistrationDto toExamRegistrationDto(ExamRegistration examRegistration);
    ExamRegistration toExamRegistration (ExamRegistrationDto examRegistrationDto);
}
