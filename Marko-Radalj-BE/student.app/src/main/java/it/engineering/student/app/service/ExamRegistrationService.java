package it.engineering.student.app.service;

import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.dto.ExamRegistrationDto;

import java.util.List;

public interface ExamRegistrationService {

    List<ExamRegistrationDto> findAll();
    ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception;

}
