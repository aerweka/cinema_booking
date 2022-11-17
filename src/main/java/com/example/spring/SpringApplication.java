package com.example.spring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SpringApplication {

        public static void main(String[] args) {
            org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        }

}
