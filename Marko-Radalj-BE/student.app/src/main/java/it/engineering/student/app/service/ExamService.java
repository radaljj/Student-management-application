package it.engineering.student.app.service;

import it.engineering.student.app.dto.ExamDto;

import java.util.List;

public interface ExamService {

    List<ExamDto> findAll();
    ExamDto save(ExamDto examDto) throws Exception;


}
