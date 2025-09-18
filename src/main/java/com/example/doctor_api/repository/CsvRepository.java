package com.example.doctor_api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CsvRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // सभी table names लाने के लिए
    public List<String> getAllTableNames() {
        return jdbcTemplate.queryForList("SHOW TABLES", String.class);
    }

    // किसी भी table का data लाने के लिए
    public List<Map<String, Object>> getTableData(String tableName) {
        return jdbcTemplate.queryForList("SELECT * FROM " + tableName);
    }
}
