package com.example.spring.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Response {

    public Map sukses(Object obj) {
        Map map = new HashMap();
        map.put("data", obj);
        map.put("code", 200);
        map.put("status", "sukses");
        return map;
    }

    public Map error(Object obj, Object code) {
        Map map = new HashMap();
        map.put("code", code);
        map.put("status", obj);
        return map;
    }
}

