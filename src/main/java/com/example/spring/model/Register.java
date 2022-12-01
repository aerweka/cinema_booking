package com.example.spring.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Register {
    @NotEmpty(message = "username is required.")
    private String username;

    @NotEmpty(message = "password is required.")
    private String password;

    @NotEmpty(message = "fullname is required.")
    private String fullname;

}
