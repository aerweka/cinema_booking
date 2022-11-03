package com.example.spring.controller.api;

import com.example.spring.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class HomesController {
    @GetMapping("/")
    public ResponseEntity<List<Student>> index(){
        List<Student> allStudent = new ArrayList<>();
        allStudent.add(new Student("1", "Ali", "L", "New York"));
        allStudent.add(new Student("2", "Ane", "P", "Texas"));
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }
}
