package com.example.spring.controller;

import com.example.spring.entity.Schedule;
import com.example.spring.repository.SchedulesRepository;
import com.example.spring.service.SchedulesService;
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
@RequestMapping("/schedules")
public class SchedulesController {
    @Autowired
    private Response response;

    @Autowired
    public SchedulesRepository schedulesRepository;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public SchedulesService schedulesService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Schedule schedule) {
        return new ResponseEntity<Map>(schedulesService.save(schedule), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{schedule_id}")
    public ResponseEntity<Map> update(@PathVariable("schedule_id") Long id, @RequestBody Schedule schedule) {
        return new ResponseEntity<Map>(schedulesService.update(schedule), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{schedule_id}"})
    public ResponseEntity<Map> delete(@PathVariable("schedule_id") Long id) throws Exception {
        return new ResponseEntity<Map>(schedulesService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long scheduleId) throws Exception {
        return new ResponseEntity<Map>(schedulesService.getById(scheduleId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Schedule> list = null;
        list = schedulesRepository.getListData(show_data);
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
