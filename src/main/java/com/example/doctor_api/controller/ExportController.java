package com.example.doctor_api.controller;

import com.example.doctor_api.service.ExcelExportService;
import com.example.doctor_api.service.ExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/export")

public class ExportController {
//    @Autowired
//    private ExportService exportService;
    @Autowired
    private ExcelExportService excelExportService;

//    @GetMapping("/all")
//    public void exportAllTables(HttpServletResponse response) throws IOException {
//        exportService.exportAllTablesToZip(response);
//    }


    @GetMapping("/excel/{username}")
    public void exportAllTables(@PathVariable String username, HttpServletResponse response) throws IOException {
        excelExportService.exportAllTablesToExcel(username,response);
    }
}