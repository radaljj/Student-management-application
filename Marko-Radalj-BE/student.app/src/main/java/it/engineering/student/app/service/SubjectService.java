package it.engineering.student.app.service;
import it.engineering.student.app.dto.SubjectDto;
import it.engineering.student.app.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubjectService {


    List<SubjectDto> findAll();

    void deleteById(Long id);
    SubjectDto update(SubjectDto subjectDto) throws  Exception;

    SubjectDto save(SubjectDto subjectDto) throws Exception;

    Optional<SubjectDto> findById(Long id);

    Page<SubjectDto> findByPage(Pageable pageable);
}
