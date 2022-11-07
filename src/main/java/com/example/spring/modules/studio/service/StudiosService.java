package com.example.spring.modules.studio.service;

import com.example.spring.modules.book.Book;
import com.example.spring.modules.studio.Studio;

import java.util.Map;

public interface StudiosService {
    Map save(Studio request);

    Map update(Studio request);

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
