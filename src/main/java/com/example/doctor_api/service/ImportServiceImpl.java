package com.example.doctor_api.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportServiceImpl implements ImportService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void importData(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {

            //  sheet = table
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String tableName = sheet.getSheetName();  //  table name

                // Index sheet "Tables"  skip
                if ("Tables".equalsIgnoreCase(tableName)) {
                    continue;
                }

                //  row = column names
                Row headerRow = sheet.getRow(0);
                int colCount = headerRow.getPhysicalNumberOfCells();
                List<String> columns = new ArrayList<>();
                for (int j = 0; j < colCount; j++) {
                    columns.add("`" + headerRow.getCell(j).getStringCellValue() + "`");
                }

                // data rows
                for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                    Row row = sheet.getRow(r);
                    if (row == null) continue;

                    List<Object> values = new ArrayList<>();
                    for (int c = 0; c < colCount; c++) {
                        Cell cell = row.getCell(c);
                        values.add(getCellValue(cell));
                    }

                    // INSERT query
                    String sql = "INSERT INTO `" + tableName + "` (" + String.join(",", columns) + ") " +
                            " VALUES (" + columns.stream().map(col -> "?").collect(Collectors.joining(",")) + ")";

                    System.out.println("Generated SQL: " + sql);
                    jdbcTemplate.update(sql, values.toArray());
                }
            }
        }
    }

    // Helper function
    private Object getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                double d = cell.getNumericCellValue();
                return (d == (int) d) ? (int) d : d;
            case BOOLEAN: return cell.getBooleanCellValue();
            default: return null;
        }
    }

    }

