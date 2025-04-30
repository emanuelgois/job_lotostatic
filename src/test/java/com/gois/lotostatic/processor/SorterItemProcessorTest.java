package com.gois.lotostatic.processor;

import static org.junit.jupiter.api.Assertions.*;

import com.gois.lotostatic.mapper.SorterMapper;
import com.gois.lotostatic.model.Sorter;
import com.gois.lotostatic.model.SorterDto;
import com.gois.lotostatic.repository.SorterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SorterItemProcessorTest {

    @Mock
    private SorterRepository sorterRepository;
    @Mock
    private SorterMapper sorterMapper;
    @InjectMocks
    private SorterItemProcessor processor;

    @Test
    void shouldReturnNullWhenConcourseAlreadyExists() {
        SorterDto dto = new SorterDto();
        dto.setConcourse(123);

        when(sorterRepository.existsByConcourse(123)).thenReturn(true);

        Sorter result = processor.process(dto);
        assertNull(result, "Deve retornar null quando concourse já existe");
    }

    @Test
    void shouldReturnMappedSorterWhenConcourseDoesNotExist() {
        SorterDto dto = new SorterDto();
        dto.setConcourse(123);
        dto.setDrawDate(LocalDate.of(2023, 1, 1));

        Sorter expected = Sorter.builder().concourse(123).drawDate(dto.getDrawDate()).build();

        when(sorterRepository.existsByConcourse(123)).thenReturn(false);
        when(sorterMapper.map(dto)).thenReturn(expected);

        Sorter result = processor.process(dto);

        assertNotNull(result, "Deve retornar o objeto mapeado");
        assertEquals(expected.getConcourse(), result.getConcourse());
        assertEquals(expected.getDrawDate(), result.getDrawDate());
    }
}