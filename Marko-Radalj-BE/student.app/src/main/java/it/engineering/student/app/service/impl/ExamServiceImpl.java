package it.engineering.student.app.service.impl;


import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.entity.*;
import it.engineering.student.app.maping.ExamMapper;
import it.engineering.student.app.repository.ExamRepository;
import it.engineering.student.app.repository.ProfessorRepository;
import it.engineering.student.app.repository.SubjectRepository;
import it.engineering.student.app.service.ExamService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {


    private final ExamRepository examRepository;
    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final ExamMapper examMapper= Mappers.getMapper(ExamMapper.class);

@Autowired
    public ExamServiceImpl(ExamRepository examRepository, ProfessorRepository professorRepository, SubjectRepository subjectRepository) {
        this.examRepository = examRepository;
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<ExamDto> findAll() {
        List<Exam> entites = (List)examRepository.findAll();
        return entites.stream().map(entity -> {
            return examMapper.toExamDto(entity);
        }).collect(Collectors.toList());    }

    @Override
    public ExamDto save(ExamDto examDto) throws Exception {
        Optional<Exam> exam = examRepository.findById(examDto.getExamId());
        if (exam.isPresent()){
            throw new Exception( "Exam already exists!");
        }
        Exam e = examMapper.toExam(examDto);
        Optional<Professor> p = professorRepository.findById(examDto.getProfessor().getProfessorId());
        Optional<Subject> s = subjectRepository.findById(examDto.getSubject().getIdSubject());

        if (p.isPresent()&& s.isPresent()){
            e.setProfessor(p.get());
            e.setSubject(s.get());
        }
        return examMapper.toExamDto(examRepository.save(e));
    }


}
