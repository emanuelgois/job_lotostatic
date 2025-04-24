package com.gois.lotostatic.reader;

import com.alibaba.excel.EasyExcel;
import com.gois.lotostatic.model.SorterDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ExcelReaderTest {

    @Autowired
    private ExcelReader excelReader;

    @Test
    void lerExcel_deveLerArquivoExcelERetornarLista() throws Exception {
        // Arrange: cria dados mockados
        SorterDto dto = new SorterDto();
        dto.setConcourse(123.0);
        dto.setDrawDate(LocalDate.of(2023, 10, 20));
        dto.setNumberOne("01");
        dto.setNumberTwo("02");
        dto.setNumberThree("03");
        dto.setNumberFour("04");
        dto.setNumberFive("05");
        dto.setNumberSix("06");

        List<SorterDto> lista = Collections.singletonList(dto);

        // Cria arquivo temporário
        File tempFile = Files.createTempFile("teste", ".xlsx").toFile();

        // Escreve no Excel
        EasyExcel.write(tempFile, SorterDto.class).sheet("Planilha").doWrite(lista);

        // Act
        List<SorterDto> resultado = excelReader.lerExcel(tempFile.getAbsolutePath());

        // Assert
        assertThat(resultado).hasSize(1);
        SorterDto resultadoDto = resultado.get(0);
        assertThat(resultadoDto.getConcourse()).isEqualTo(123.0);
        assertThat(resultadoDto.getDrawDate()).isEqualTo(LocalDate.of(2023, 10, 20));
        assertThat(resultadoDto.getNumberOne()).isEqualTo("01");

        // Clean up
        tempFile.delete();
    }
}