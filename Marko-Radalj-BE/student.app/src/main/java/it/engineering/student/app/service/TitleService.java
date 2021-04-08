package it.engineering.student.app.service;
import it.engineering.student.app.dto.TitleDto;

import java.util.List;

public interface TitleService {

    List<TitleDto> findAll();

}
