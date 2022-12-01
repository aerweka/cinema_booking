package com.example.spring.controller;

import com.example.spring.config.Config;
import com.example.spring.entity.User;
import com.example.spring.model.Register;
import com.example.spring.repository.UsersRepository;
import com.example.spring.service.UsersService;
import com.example.spring.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user-register/")
public class RegisterController {
    @Autowired
    private UsersRepository usersRepository;

    Config config = new Config();

    @Autowired
    public UsersService usersService;

    @Autowired
    public Response templateCRUD;

    @PostMapping("/register")
    public ResponseEntity<Map> saveRegisterManual(@Valid
                                                  @RequestBody Register objModel) throws RuntimeException {
        Map map = new HashMap();

        User user = usersRepository.checkExistingEmail(objModel.getUsername());
        if (null != user) {
            return new ResponseEntity<Map>(templateCRUD.notFound("Username sudah ada"), HttpStatus.OK);

        }
        map = usersService.registerManual(objModel);

        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
