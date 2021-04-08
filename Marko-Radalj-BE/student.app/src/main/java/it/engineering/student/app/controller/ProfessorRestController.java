package it.engineering.student.app.controller;
import it.engineering.student.app.dto.ProfessorDto;
import it.engineering.student.app.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("professor")
public class ProfessorRestController {
    @Autowired
    private ProfessorService professorService;


    @GetMapping("getProfessors")
    public @ResponseBody
    ResponseEntity<List<ProfessorDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll());
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
        Optional<ProfessorDto> professorDto = professorService.findById(id);
        if (professorDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid professor id!");
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ProfessorDto professorDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(professorService.save(professorDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
        professorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Professor deleted");
    }


    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ProfessorDto professorDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(professorService.update(professorDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
