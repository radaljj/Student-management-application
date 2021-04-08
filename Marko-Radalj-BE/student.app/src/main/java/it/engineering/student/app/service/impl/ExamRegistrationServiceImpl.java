package it.engineering.student.app.service.impl;

import it.engineering.student.app.dto.ExamRegistrationDto;
import it.engineering.student.app.entity.*;
import it.engineering.student.app.maping.ExamMapper;
import it.engineering.student.app.maping.ExamRegistrationMapper;
import it.engineering.student.app.repository.*;
import it.engineering.student.app.service.ExamRegistrationService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamRegistrationServiceImpl implements ExamRegistrationService {

    private final ExamRegistrationRepository examRegistrationRepository;
    private final ExamRepository examRepository;
    private final StudentRepository studentRepository;
    private final ExamRegistrationMapper examRegistrationMapper= Mappers.getMapper(ExamRegistrationMapper.class);


    @Autowired
    public ExamRegistrationServiceImpl(ExamRegistrationRepository examRegistrationRepository, ExamRepository examRepository, StudentRepository studentRepository) {
        this.examRegistrationRepository = examRegistrationRepository;
        this.examRepository = examRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<ExamRegistrationDto> findAll() {
        List<ExamRegistration> entites = (List)examRegistrationRepository.findAll();
        return entites.stream().map(entity -> {
            return examRegistrationMapper.toExamRegistrationDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws Exception {
        Optional<ExamRegistration> examRegistration = examRegistrationRepository.findById(examRegistrationDto.getExamRegistrationId());
        if (examRegistration.isPresent()){
            throw new Exception( "Exam registration already exists!");
        }
        ExamRegistration e = examRegistrationMapper.toExamRegistration(examRegistrationDto);
        Optional<Exam> exam = examRepository.findById(examRegistrationDto.getExam().getExamId());
        Optional<Student> student = studentRepository.findById(examRegistrationDto.getStudent().getStudentId());

        if (exam.isPresent()&& student.isPresent()){
            e.setExam(exam.get());
            e.setStudent(student.get());
        }
        return examRegistrationMapper.toExamRegistrationDto(examRegistrationRepository.save(e));
    }
}
