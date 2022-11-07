package com.example.spring.modules.seat.service;

import com.example.spring.modules.seat.Seat;

import java.util.Map;

public interface SeatsService {
    Map save(Seat request);

    Map update(Seat request);

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
