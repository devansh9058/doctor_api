package com.example.doctor_api.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ExportService {
    void exportAllTablesToZip(HttpServletResponse response) throws IOException;
}
