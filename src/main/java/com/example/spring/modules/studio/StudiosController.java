package com.example.spring.modules.studio;

import com.example.spring.modules.book.Book;
import com.example.spring.modules.book.BooksRepo;
import com.example.spring.modules.book.service.BooksService;
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
public class StudiosController {
    @Autowired
    private Response response;

    @Autowired
    public BooksRepo bookRepo;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    public BooksService bookService;

    @PostMapping(value = {"/save", "/save/"})
    public ResponseEntity<Map> save(@RequestBody com.example.spring.modules.book.Book film) {
        return new ResponseEntity<Map>(bookService.save(film), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{film_id}")
    public ResponseEntity<Map> update(@PathVariable("film_id") Long id, @RequestBody com.example.spring.modules.book.Book film) {
        return new ResponseEntity<Map>(bookService.update(film), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/delete/{film_id}"})
    public ResponseEntity<Map> delete(@PathVariable("film_id") Long id) throws Exception {
        return new ResponseEntity<Map>(bookService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long bookId) throws Exception {
        return new ResponseEntity<Map>(bookService.getById(bookId), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<Book> list = null;
        list = bookRepo.getListData(show_data);
        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}