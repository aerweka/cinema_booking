package com.example.spring.controller;

import com.example.spring.entity.Studio;
import com.example.spring.repository.StudiosRepository;
import com.example.spring.service.StudiosService;
import com.example.spring.utils.Response;
import com.example.spring.utils.SimpleStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/studios")
public class StudiosController {
    @Autowired
    private Response response;

    @Autowired
    public StudiosRepository studiosRepository;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public StudiosService studiosService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Studio studio) {
        return new ResponseEntity<Map>(studiosService.save(studio), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{studio_id}")
    public ResponseEntity<Map> update(@PathVariable("studio_id") Long id, @RequestBody Studio studio) {
        return new ResponseEntity<Map>(studiosService.update(studio), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{studio_id}"})
    public ResponseEntity<Map> delete(@PathVariable("studio_id") Long id) throws Exception {
        return new ResponseEntity<Map>(studiosService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long bookId) throws Exception {
        return new ResponseEntity<Map>(studiosService.getById(bookId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Studio> list = null;
        list = studiosRepository.getListData(show_data);
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
