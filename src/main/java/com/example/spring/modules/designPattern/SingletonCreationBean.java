package com.example.spring.modules.designPattern;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingletonCreationBean {
    @Bean
    public SingletonCreationBean singletonBean() {
        System.out.println("create singleton bean");
        return new SingletonCreationBean();
    }
}
