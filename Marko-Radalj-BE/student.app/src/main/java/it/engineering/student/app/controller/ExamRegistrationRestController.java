package it.engineering.student.app.controller;


import it.engineering.student.app.dto.ExamDto;
import it.engineering.student.app.dto.ExamRegistrationDto;
import it.engineering.student.app.service.ExamRegistrationService;
import it.engineering.student.app.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("registration")
public class ExamRegistrationRestController {


    @Autowired
    private ExamRegistrationService examRegistrationService;


    @GetMapping("getRegistration")
    public @ResponseBody
    ResponseEntity<List<ExamRegistrationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(examRegistrationService.findAll());
    }

    @PostMapping()
    public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamRegistrationDto examRegistrationDto ) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(examRegistrationService.save(examRegistrationDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }



}
