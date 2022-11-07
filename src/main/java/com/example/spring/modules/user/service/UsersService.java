package com.example.spring.modules.user.service;

import com.example.spring.modules.user.User;

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
