package it.engineering.student.app.controller;


import it.engineering.student.app.dto.CityDto;
import it.engineering.student.app.dto.TitleDto;
import it.engineering.student.app.service.CityService;
import it.engineering.student.app.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class CityTitleController {

    @Autowired
    private  CityService cityService;
    @Autowired
    private  TitleService titleService;


    @GetMapping("getCities")
    public @ResponseBody
    ResponseEntity<List<CityDto>> getAllCities() {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
    }


    @GetMapping("getTitles")
    public @ResponseBody
    ResponseEntity<List<TitleDto>> getAllTitles() {
        return ResponseEntity.status(HttpStatus.OK).body(titleService.findAll());
    }

}
