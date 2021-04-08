package it.engineering.student.app.maping;

import it.engineering.student.app.dto.StudentDto;
import it.engineering.student.app.entity.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentDto toStudentDto(Student student);
    Student toStudent(StudentDto studentDto);
}