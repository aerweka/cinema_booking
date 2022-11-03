package com.example.spring.service.impl;

import com.example.spring.model.Employee;
import com.example.spring.service.EmployeesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeesServiceImpl implements EmployeesService {
    @Override
    public ResponseEntity<List<Employee>> get() throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Employee> getById(String id) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Employee> store(Map<String, Employee> employee) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<Employee> update(String id, Map<String, Employee> employee) throws Exception {
        return null;
    }

    @Override
    public String delete(String id) throws Exception {
        return null;
    }
}
