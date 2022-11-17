package com.example.spring.controller;

import com.example.spring.entity.Film;
import com.example.spring.repository.FilmsRepository;
import com.example.spring.service.FilmsService;
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
@RequestMapping("/films")
public class FilmsController {
    @Autowired
    private Response response;

    @Autowired
    public FilmsRepository filmsRepository;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public FilmsService filmsService;

    @PostMapping("/")
    public ResponseEntity<Map> save(@RequestBody Film film) {
        return new ResponseEntity<Map>(filmsService.save(film), HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Map> update(@RequestBody Film film) {
        return new ResponseEntity<Map>(filmsService.update(film), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{film_id}"})
    public ResponseEntity<Map> delete(@PathVariable("film_id") Long id) throws Exception {
        return new ResponseEntity<Map>(filmsService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long bookId) throws Exception {
        return new ResponseEntity<Map>(filmsService.getById(bookId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map> get(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) Boolean isPlayed,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Film> list = null;
        list = isPlayed != null ? filmsRepository.findByIsPlayed(isPlayed, show_data) : filmsRepository.getListData(show_data);
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
