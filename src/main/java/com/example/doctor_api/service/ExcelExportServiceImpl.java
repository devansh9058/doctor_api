package com.example.doctor_api.service;

import com.example.doctor_api.repository.ExportRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ExcelExportServiceImpl implements ExcelExportService{
    @Autowired
    private ExportRepository exportRepository;

    public void exportAllTablesToExcel(String username,HttpServletResponse response) {
       // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("text/xlsx");
        String fileName = username + "_report.csv";  // e.g. Devansh_report.csv
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
       // response.setHeader("Content-Disposition", "attachment; filename=all_tables.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // 1️⃣ Index sheet बनाओ
            XSSFSheet indexSheet = workbook.createSheet("Tables");
            int rowIdx = 0;

            List<String> tables = exportRepository.getAllTableNames();

            for (String tableName : tables) {
                Row row = indexSheet.createRow(rowIdx++);
                Cell cell = row.createCell(0);
                cell.setCellValue(tableName);

                // hyperlink to table sheet
                CreationHelper helper = workbook.getCreationHelper();
                Hyperlink link = helper.createHyperlink(HyperlinkType.DOCUMENT);
                link.setAddress("'" + tableName + "'!A1");
                cell.setHyperlink(link);
            }

            // 2️⃣ हर table का data अलग sheet पर
            for (String tableName : tables) {
                List<Map<String, Object>> rows = exportRepository.getTableData(tableName);
                if (rows.isEmpty()) continue;

                XSSFSheet sheet = workbook.createSheet(tableName);

                // Header
                Row headerRow = sheet.createRow(0);
                Set<String> columns = rows.get(0).keySet();
                int colIdx = 0;
                for (String col : columns) {
                    headerRow.createCell(colIdx++).setCellValue(col);
                }

                // Data
                int dataRowIdx = 1;
                for (Map<String, Object> rowData : rows) {
                    Row row = sheet.createRow(dataRowIdx++);
                    colIdx = 0;
                    for (String col : columns) {
                        Object val = rowData.get(col);
                        row.createCell(colIdx++).setCellValue(val != null ? val.toString() : "");
                    }
                }
            }

            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
