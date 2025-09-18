package com.example.doctor_api.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface CsvExportService {


    void exportAllTablesToCsv(HttpServletResponse response) throws IOException;
}
