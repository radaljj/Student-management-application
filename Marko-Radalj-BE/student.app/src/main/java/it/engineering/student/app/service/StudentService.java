package it.engineering.student.app.service;

import it.engineering.student.app.dto.StudentDto;
import it.engineering.student.app.dto.SubjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;


public interface StudentService {

    List<StudentDto> findAll();

    void deleteById(Long id);

    StudentDto update(StudentDto studentDto) throws  Exception;

    StudentDto save(StudentDto studentDto) throws Exception;

    Optional<StudentDto> findById(Long id);

    Page<StudentDto> findByPage(Pageable pageable);






}
