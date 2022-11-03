package com.example.spring.service;

import com.example.spring.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeesService {
    ResponseEntity<List<Employee>> get() throws Exception;

    ResponseEntity<Employee> getById(String id) throws Exception;

    ResponseEntity<Employee> store(Map<String, Employee> employee) throws Exception;

    ResponseEntity<Employee> update(String id, Map<String, Employee> employee) throws Exception;

    String delete(String id) throws Exception;
}
