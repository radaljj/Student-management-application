package it.engineering.student.app.controller;

import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("exam")
public class ExamRestController {

    @Autowired
    private ExamService examService;


    @GetMapping("getExams")
    public @ResponseBody
    ResponseEntity<List<ExamDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(examService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamDto examDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(examService.save(examDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
