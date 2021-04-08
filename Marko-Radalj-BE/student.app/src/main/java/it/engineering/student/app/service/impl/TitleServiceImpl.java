package it.engineering.student.app.service.impl;

import it.engineering.student.app.dto.TitleDto;
import it.engineering.student.app.entity.Title;
import it.engineering.student.app.maping.TitleMapper;
import it.engineering.student.app.repository.TitleRepository;
import it.engineering.student.app.service.TitleService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {

    private final TitleRepository titleRepository;
    private final TitleMapper titleMapper= Mappers.getMapper(TitleMapper.class);

    @Autowired
    public TitleServiceImpl(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public List<TitleDto> findAll() {
        List<Title> entites = (List)titleRepository.findAll();
        return entites.stream().map(entity -> {
            return titleMapper.toTitleDto(entity);
        }).collect(Collectors.toList());

    }
}
