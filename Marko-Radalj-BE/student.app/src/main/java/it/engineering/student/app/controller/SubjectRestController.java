package it.engineering.student.app.controller;
import it.engineering.student.app.dto.SubjectDto;
import it.engineering.student.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("subjects")
public class SubjectRestController {
    @Autowired
    private SubjectService subjectService;


    @GetMapping("getSubjects")
    public @ResponseBody
    ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());

    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
        Optional<SubjectDto> cityDto = subjectService.findById(id);
        if (cityDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(cityDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid subject id!");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody SubjectDto subjectDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.save(subjectDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        subjectService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Subject deleted");
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(subjectService.update(subjectDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
