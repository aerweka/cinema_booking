package com.example.spring.bean;

import org.springframework.stereotype.Component;


@Component // bertujuan men scan mencari : supaya spring boot dapat mengenali class , seblum aplikasi ready
public class Message {
    public String getMessage() {
        return "Hello World! == isi bean";
    }

    public String getNama() {
        return "Riki aldi";
    }
}
