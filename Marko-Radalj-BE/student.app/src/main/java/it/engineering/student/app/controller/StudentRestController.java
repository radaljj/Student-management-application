package it.engineering.student.app.controller;
import it.engineering.student.app.dto.StudentDto;
import it.engineering.student.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("students")
public class StudentRestController {

    @Autowired
private  StudentService studentService;

    @GetMapping("getStudents")
    public @ResponseBody
    ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
    }


    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
        Optional<StudentDto> cityDto = studentService.findById(id);
        if (cityDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cityDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student id!");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }


    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.update(studentDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
