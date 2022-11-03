package com.example.spring.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Employee {
    private List<Employee> allEmployees = new ArrayList<>();

    private String id;

    private String name;

    private String dob;

    private String address;

    private Integer salary;
}
