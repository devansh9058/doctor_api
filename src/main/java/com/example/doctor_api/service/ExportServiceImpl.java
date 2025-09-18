package com.example.doctor_api.service;

import com.example.doctor_api.repository.ExportRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ExportServiceImpl implements ExportService {
    @Autowired
    private ExportRepository exportRepository;
    @Override
    public void exportAllTablesToZip(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=all_tables.zip");

        try (ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream())) {
            List<String> tables = exportRepository.getAllTableNames();

            for (String tableName : tables) {
                // Add new CSV entry in zip
                ZipEntry entry = new ZipEntry(tableName + ".csv");
                zipOut.putNextEntry(entry);

                PrintWriter writer = new PrintWriter(new OutputStreamWriter(zipOut));

                List<Map<String, Object>> rows = exportRepository.getTableData(tableName);

                if (!rows.isEmpty()) {
                    // Header row
                    Set<String> columns = rows.get(0).keySet();
                    writer.println(String.join(",", columns));

                    // Data rows
                    for (Map<String, Object> row : rows) {
                        List<String> values = new ArrayList<>();
                        for (String col : columns) {
                            values.add(row.get(col) != null ? row.get(col).toString() : "");
                        }
                        writer.println(String.join(",", values));
                    }
                }

                writer.flush();
                zipOut.closeEntry();
            }
        }
    }
    }

