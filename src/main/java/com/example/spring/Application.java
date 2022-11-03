package com.example.spring;

import com.example.spring.bean.ApplicationContextProvider;
import com.example.spring.bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired // DI : dependency injektion : guna untuk melakukan injeksi terhadap class: supaya spring mspring boot dapat mengenalnya
    ApplicationContextProvider applicationContextProvider;

    @Autowired
    Message message;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
// memanggil beans
        Message message = applicationContextProvider.getApplicationContext().getBean(Message.class);
        System.out.println(message.getMessage());
        System.out.println(message.getNama());
    }

}
