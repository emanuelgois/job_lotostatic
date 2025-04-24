package com.gois.lotostatic.util;

import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateConverterTest {

    private LocalDateConverter converter;

    @BeforeEach
    void setUp() {
        converter = new LocalDateConverter();
    }

    @Test
    void convertToJavaData_deveConverterStringParaLocalDate() throws Exception {
        // Arrange
        ReadCellData<String> cellData = new ReadCellData<>("11/03/1996");

        // Act
        LocalDate result = converter.convertToJavaData(
                cellData,
                null,
                new GlobalConfiguration()
        );

        // Assert
        assertThat(result).isEqualTo(LocalDate.of(1996, 3, 11));
    }

    @Test
    void convertToExcelData_deveConverterLocalDateParaStringFormatada() throws Exception {
        // Arrange
        LocalDate date = LocalDate.of(1996, 3, 11);

        // Act
        WriteCellData<?> result = converter.convertToExcelData(
                date,
                null,
                new GlobalConfiguration()
        );

        // Assert
        assertThat(result.getStringValue()).isEqualTo("11/03/1996");
    }
}