package com.gois.lotostatic;

import com.gois.lotostatic.model.Sorter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

public class ExcelItemReader implements ItemReader<Sorter> {

    private Iterator<Row> rowIterator;

    public ExcelItemReader(String filePath) throws IOException {
        InputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // primeira aba
        this.rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) rowIterator.next(); // pula cabeçalho
    }

    @Override
    public Sorter read() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            return Sorter.builder()
                    .concourse(row.getCell(0).getNumericCellValue())
                    .drawDate(LocalDate.parse(row.getCell(1).getStringCellValue(), formatter))
                    .numberOne(String.valueOf(row.getCell(2).getNumericCellValue()))
                    .numberTwo(String.valueOf(row.getCell(3).getNumericCellValue()))
                    .numberThree(String.valueOf(row.getCell(4).getNumericCellValue()))
                    .numberFour(String.valueOf(row.getCell(5).getNumericCellValue()))
                    .numberFive(String.valueOf(row.getCell(6).getNumericCellValue()))
                    .numberSix(String.valueOf(row.getCell(7).getNumericCellValue()))
                    .build();
        }
        return null;
    }
}
