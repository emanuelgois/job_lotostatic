package com.gois.lotostatic;

import com.gois.lotostatic.model.MegaSena;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.ItemReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExcelItemReader implements ItemReader<MegaSena> {

    private Iterator<Row> rowIterator;

    public ExcelItemReader(String filePath) throws IOException {
        InputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // primeira aba
        this.rowIterator = sheet.iterator();

        if (rowIterator.hasNext()) rowIterator.next(); // pula cabeçalho
    }

    @Override
    public MegaSena read() {
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            var sorteio = new MegaSena(Long.getLong(row.getCell(0).getStringCellValue()));
            return sorteio;
        }
        return null;
    }
}
