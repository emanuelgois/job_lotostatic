package com.gois.lotostatic.mapper;


import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.model.SorterDto;
import org.springframework.stereotype.Component;

@Component
public class SorterMapper {

    public Sorter map(SorterDto dto){
        return Sorter.builder()
                .concourse(dto.getConcourse())
                .drawDate(dto.getDrawDate())
                .numberOne(dto.getNumberOne())
                .numberTwo(dto.getNumberTwo())
                .numberThree(dto.getNumberThree())
                .numberFour(dto.getNumberFour())
                .numberFive(dto.getNumberFive())
                .numberSix(dto.getNumberSix())
                .build();

    }
}
