package it.engineering.student.app.maping;

import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.entity.Exam;
import org.mapstruct.Mapper;

@Mapper
public interface ExamMapper {

    ExamDto toExamDto(Exam exam);
    Exam toExam(ExamDto examDto);
}
