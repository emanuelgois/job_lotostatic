package com.gois.lotostatic.mapper;

import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.model.SorterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SorterMapperTest {

    private SorterMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new SorterMapper();
    }

    @Test
    void map_deveConverterDtoParaEntidadeCorretamente() {
        // Arrange
        SorterDto dto = new SorterDto();
        dto.setConcourse(456.0);
        dto.setDrawDate(LocalDate.of(2024, 5, 10));
        dto.setNumberOne("10");
        dto.setNumberTwo("20");
        dto.setNumberThree("30");
        dto.setNumberFour("40");
        dto.setNumberFive("50");
        dto.setNumberSix("60");

        // Act
        Sorter entity = mapper.map(dto);

        // Assert
        assertThat(entity.getConcourse()).isEqualTo(456.0);
        assertThat(entity.getDrawDate()).isEqualTo(LocalDate.of(2024, 5, 10));
        assertThat(entity.getNumberOne()).isEqualTo("10");
        assertThat(entity.getNumberSix()).isEqualTo("60");
    }
}