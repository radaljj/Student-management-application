package it.engineering.student.app.maping;

import it.engineering.student.app.dto.TitleDto;
import it.engineering.student.app.entity.Title;
import org.mapstruct.Mapper;

@Mapper
public interface TitleMapper {

    TitleDto toTitleDto(Title title);
    Title toTitle(TitleDto titleDto);
}
