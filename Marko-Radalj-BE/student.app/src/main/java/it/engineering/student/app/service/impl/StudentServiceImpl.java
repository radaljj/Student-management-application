package it.engineering.student.app.service.impl;
import it.engineering.student.app.dto.StudentDto;
import it.engineering.student.app.entity.City;
import it.engineering.student.app.entity.Student;
import it.engineering.student.app.maping.StudentMapper;
import it.engineering.student.app.repository.CityRepository;
import it.engineering.student.app.repository.StudentRepository;
import it.engineering.student.app.service.StudentService;
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
public class StudentServiceImpl implements StudentService {
  private final   StudentRepository studentRepository;
  private final CityRepository cityRepository;
  private final StudentMapper studentMapper= Mappers.getMapper(StudentMapper.class);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CityRepository cityRepository) {
        this.studentRepository = studentRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> entites = (List)studentRepository.findAll();
        return entites.stream().map(entity -> {
            return studentMapper.toStudentDto(entity);
        }).collect(Collectors.toList());

    }

    @Override
    public void deleteById(Long id) {

studentRepository.deleteById(id);
    }

    @Override
    public StudentDto update(StudentDto studentDto) throws Exception {
        Optional<Student> entity = studentRepository.findById(studentDto.getStudentId());
        if (!entity.isPresent()) {
            throw new Exception( "Student does not exist!");
        }
        Student s = studentRepository.save(studentMapper.toStudent(studentDto));
        Optional<City> city = cityRepository.findById(studentDto.getCity().getCityId());
        if (city.isPresent()){
            s.setCity(city.get());
        }
        return studentMapper.toStudentDto(studentRepository.save(s));
    }

    @Override
    public StudentDto save(StudentDto studentDto) throws Exception {
        Optional<Student> student = studentRepository.findById(studentDto.getStudentId());
        if (student.isPresent()){
            throw new Exception( "Student already exists!");
        }
        Student s = studentMapper.toStudent(studentDto);
        Optional<City> city = cityRepository.findById(studentDto.getCity().getCityId());
        if (city.isPresent()){
            s.setCity(city.get());
        }
        return studentMapper.toStudentDto(studentRepository.save(s));
    }

    @Override
    public Optional<StudentDto> findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return Optional.of(studentMapper.toStudentDto(student.get()));
        }
        return Optional.empty();
    }

    @Override
    public Page<StudentDto> findByPage(Pageable pageable) {
        Page<StudentDto> entites = studentRepository.findAll(pageable).map(studentMapper::toStudentDto);
        return entites;
    }


}
