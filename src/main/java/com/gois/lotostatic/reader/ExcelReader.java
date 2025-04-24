package com.gois.lotostatic.reader;

import com.alibaba.excel.EasyExcel;
import com.gois.lotostatic.model.SorterDto;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class ExcelReader {

    public List<SorterDto> lerExcel(String caminhoArquivo) {
        return EasyExcel.read(new File(caminhoArquivo))
                .head(SorterDto.class)
                .sheet() // lê a primeira planilha
                .doReadSync(); // retorna a lista diretamente
    }
}