package com.example.doctor_api.service;

import com.example.doctor_api.repository.CsvRepository;
import com.example.doctor_api.repository.ExportRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class CsvExportServiceImpl implements CsvExportService{
    @Autowired
    private ExportRepository exportRepository;

    @Override
    public void exportAllTablesToCsv(HttpServletResponse response) throws IOException {
        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            List<String> tables = exportRepository.getAllTableNames();

            for (String tableName : tables) {
                List<Map<String, Object>> rows = exportRepository.getTableData(tableName);

                // ZIP में entry add करो (tableName.csv)
                ZipEntry entry = new ZipEntry(tableName + ".csv");
                zipOut.putNextEntry(entry);

                // CSV writer
                StringWriter writer = new StringWriter();
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);

                if (!rows.isEmpty()) {
                    // Header
                    Set<String> columns = rows.get(0).keySet();
                    csvPrinter.printRecord(columns);

                    // Data rows
                    for (Map<String, Object> row : rows) {
                        List<Object> values = new ArrayList<>();
                        for (String col : columns) {
                            values.add(row.get(col));
                        }
                        csvPrinter.printRecord(values);
                    }
                }

                csvPrinter.flush();
                byte[] csvBytes = writer.toString().getBytes();
                zipOut.write(csvBytes);
                zipOut.closeEntry();
            }

            zipOut.finish();
        }
    }
    }

