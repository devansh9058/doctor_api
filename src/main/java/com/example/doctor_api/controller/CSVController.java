package com.example.doctor_api.controller;

import com.example.doctor_api.service.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")
public class CSVController {
    @Autowired
    private CsvExportService csvExportService;

    @GetMapping("/csv")
    public void exportAllTablesToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=all_tables.zip");

        csvExportService.exportAllTablesToCsv(response);
    }
}
