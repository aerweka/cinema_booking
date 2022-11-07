package com.example.spring.modules.schedule.service;

import com.example.spring.modules.schedule.Schedule;

import java.util.Map;

public interface SchedulesService {
    Map save(Schedule request);

    Map update(Schedule request);

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
