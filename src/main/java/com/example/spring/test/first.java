package com.example.spring.test;

import com.example.spring.modules.designPattern.SingletonCreationBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class first {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void id() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
//        String request ="";
//        // request Body
//        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        Long idFilm = 1L;
//        String url = "http://localhost:9091/api/v1/binar/supplier/" + idSupplier;
        String url = "http://localhost:9091/api/films/" + idFilm;
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Object.class);

        System.out.println("response  =" + exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void eskternalProvinsi() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
//        String request ="";
//        // request Body
//        HttpEntity<String> entity = new HttpEntity<String>(request, headers);
        String url = "https://dev.farizdotid.com/api/daerahindonesia/provinsi";
        ResponseEntity<Object> exchange = restTemplate.exchange(url, HttpMethod.GET, null, Object.class);

        System.out.println("response  =" + exchange.getBody());
//        assertEquals(HttpStatus.OK, exchange.getStatusCode());
    }

    @Test
    public void insertSuppliermap() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        Map bodyTesting = new HashMap();
        bodyTesting.put("nama", "nama");
        bodyTesting.put("hp", "445654654");
        bodyTesting.put("alamat", "alamat53534");
        HttpEntity<Map> entity = new HttpEntity<Map>(bodyTesting, headers);

        ResponseEntity<Map> exchange = restTemplate.exchange("https://dev.farizdotid.com/api/daerahindonesia/provinsi", HttpMethod.POST, entity, Map.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response  =" + exchange.getBody());
    }

    @Test
    public void insertSupplier() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "*/*");
        headers.set("Content-Type", "application/json");
        String bodyTesting = "{\n" +
                "    \"nama\":\"supplier 2sss\",\n" +
                "    \"hp\":\"098765462\",\n" +
                "    \"alamat\":\"jl sudirman2\"\n" +
                "}";
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting, headers);

        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:9091/api/v1/binar/supplier/save", HttpMethod.POST, entity, String.class);

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        System.out.println("response  =" + exchange.getBody());
    }

    @Test
    public void testSingleton() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SingletonCreationBean.class);
    }

}
