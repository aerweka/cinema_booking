package com.example.spring.service;

import com.example.spring.entity.User;
import com.example.spring.model.ModelResponse;

import java.util.List;
import java.util.Map;

public interface UsersService {
    public Map save(User request);

    public  Map update(User request);

    public Map delete(Long id);

    public Map getById(Long request);

    
    /** versiku
     ModelResponse<List<User>> get() throws Exception;

     ModelResponse<User> getById(Long id) throws Exception;

     ModelResponse<User> store(Map<String, Object> body) throws Exception;

     ModelResponse<User> update(Long id, Map<String, Object> body) throws Exception;

     ModelResponse<User> delete(Long id) throws Exception;
     */
}
