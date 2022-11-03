package com.example.spring.controller.web;

import com.example.spring.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1/web")
public class HomeController {

    @GetMapping(value = "/")
    public String index(Model model) {
        List<Student> allStudent = new ArrayList<>();
        allStudent.add(new Student("1", "Ali", "L", "New York"));
        allStudent.add(new Student("2", "Ane", "P", "Texas"));
        model.addAttribute("students",allStudent);
        return "index";
    }
}
