package com.example.spring.service;

import com.example.spring.entity.Film;
import com.example.spring.entity.User;
import com.example.spring.model.ModelResponse;

import java.util.List;
import java.util.Map;

public interface FilmsService {
    Map save(Film request);

    Map update(Film request);

    Map delete(Long id);

    Map getById(Long request);
    /**
     ModelResponse<List<Film>> get() throws Exception;

     ModelResponse<Film> getById(Long id) throws Exception;

     ModelResponse<Film> store(Map<String, Object> body) throws Exception;

     ModelResponse<Film> update(Long id, Map<String, Object> body) throws Exception;

     ModelResponse<Film> delete(Long id) throws Exception;
     */
}
