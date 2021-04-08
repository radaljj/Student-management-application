package it.engineering.student.app.service.impl;

import it.engineering.student.app.dto.SubjectDto;
import it.engineering.student.app.entity.Student;
import it.engineering.student.app.entity.Subject;
import it.engineering.student.app.maping.StudentMapper;
import it.engineering.student.app.maping.SubjectMapper;
import it.engineering.student.app.repository.SubjectRepository;
import it.engineering.student.app.service.SubjectService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper= Mappers.getMapper(SubjectMapper.class);


    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectDto> findAll() {
        List<Subject> entites = (List)subjectRepository.findAll();
        return entites.stream().map(entity -> {
            return subjectMapper.toSubjectDto(entity);
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
subjectRepository.deleteById(id);
    }

    @Override
    public SubjectDto update(SubjectDto subjectDto) throws Exception {
        Optional<Subject> entity = subjectRepository.findById(subjectDto.getIdSubject());
        if (!entity.isPresent()) {
            throw new Exception( "Subject does not exist!");
        }
        Subject s = subjectRepository.save(subjectMapper.toSubject(subjectDto));
        return subjectMapper.toSubjectDto(s);
    }


    @Override
    public SubjectDto save(SubjectDto subjectDto) throws Exception {
        Optional<Subject> subject = subjectRepository.findById(subjectDto.getIdSubject());
        if (subject.isPresent()){
            throw new Exception("Subject already exists");
        }
        Subject s = subjectRepository.saveAndFlush(subjectMapper.toSubject(subjectDto));
        return subjectMapper.toSubjectDto(s);
    }

    @Override
    public Optional<SubjectDto> findById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return Optional.of(subjectMapper.toSubjectDto(subject.get()));
        }
        return Optional.empty();
    }


    @Override
    public Page<SubjectDto> findByPage(Pageable pageable) {
        return null;
    }
}
