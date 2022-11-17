package com.example.spring.service;

import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.Map;

public interface InvoicesService {
//    ResponseEntity<byte[]> generateInvoice() throws Exception;

    byte[] generatePdf(Map<String, Object> parameters, String reportName) throws SQLException;
}
