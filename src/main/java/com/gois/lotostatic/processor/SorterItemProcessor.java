package com.gois.lotostatic.processor;

import com.gois.lotostatic.mapper.SorterMapper;
import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.model.SorterDto;
import com.gois.lotostatic.repository.SorterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SorterItemProcessor implements ItemProcessor<SorterDto, Sorter> {

    @Autowired
    private SorterRepository repository;

    @Autowired
    private SorterMapper mapper;

    @Override
    public Sorter process(SorterDto dto) {
        boolean exists = repository.existsByConcourse(dto.getConcourse()); // ou drawDate
        if (exists) {
            log.info("Registro concourse {}, drawDate {} já existe na base de dados!", dto.getConcourse(), dto.getDrawDate());
            return null;
        }
        return mapper.map(dto);
    }
}