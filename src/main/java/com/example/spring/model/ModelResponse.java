package com.example.spring.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Setter
@Getter
@Embeddable
public class ModelResponse<T> {
    private Boolean success = true;

    private Integer status = 200;

    private String message;

    private T data;
}
