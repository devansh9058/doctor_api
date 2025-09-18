package com.example.doctor_api.service;

import jakarta.servlet.http.HttpServletResponse;

public interface ExcelExportService {
    void exportAllTablesToExcel(String username,HttpServletResponse response);
}
