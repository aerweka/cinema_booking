package com.example.spring.modules.seat;

import com.example.spring.modules.seat.service.SeatsService;
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
@RequestMapping("/seats")
public class SeatsController {
    @Autowired
    private Response response;

    @Autowired
    public SeatsRepo seatsRepo;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public SeatsService seatsService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody Seat film) {
        return new ResponseEntity<Map>(seatsService.save(film), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{seat_id}")
    public ResponseEntity<Map> update(@PathVariable("seat_id") Long id, @RequestBody Seat film) {
        return new ResponseEntity<Map>(seatsService.update(film), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{seat_id}"})
    public ResponseEntity<Map> delete(@PathVariable("seat_id") Long id) throws Exception {
        return new ResponseEntity<Map>(seatsService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long seatId) throws Exception {
        return new ResponseEntity<Map>(seatsService.getById(seatId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Seat> list = null;
        list = seatsRepo.getListData(show_data);
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
