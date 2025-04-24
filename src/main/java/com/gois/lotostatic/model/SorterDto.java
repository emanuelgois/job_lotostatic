package com.gois.lotostatic.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gois.lotostatic.util.LocalDateConverter;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SorterDto {

    @ExcelProperty("Concurso")
    private double concourse;
    @ExcelProperty(value = "Data do Sorteio", converter = LocalDateConverter.class)
    private LocalDate drawDate;
    @ExcelProperty("Bola1")
    private String numberOne;
    @ExcelProperty("Bola2")
    private String numberTwo;
    @ExcelProperty("Bola3")
    private String numberThree;
    @ExcelProperty("Bola4")
    private String numberFour;
    @ExcelProperty("Bola5")
    private String numberFive;
    @ExcelProperty("Bola6")
    private String numberSix;
}
