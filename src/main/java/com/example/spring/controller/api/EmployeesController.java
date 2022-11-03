package com.example.spring.controller.api;

import com.example.spring.model.Employee;
import com.example.spring.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeesController {

    @Autowired
    EmployeesService employeesService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> index() throws Exception {
        return employeesService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") String id) throws Exception {
        return employeesService.getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Employee> store(@RequestBody Map<String, Employee> employee) throws Exception {
        return employeesService.store(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> store(@PathVariable("id") String id, @RequestBody Map<String, Employee> employee) throws Exception {
        return employeesService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) throws Exception {
        return employeesService.delete(id);
    }
}
