package com.example.spring.controller;

import com.example.spring.entity.User;
import com.example.spring.repository.UsersRepository;
import com.example.spring.service.UsersService;
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
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private Response response;

    @Autowired
    private UsersRepository usersRepository;

    SimpleStringUtils simpleStringUtils = new SimpleStringUtils();

    @Autowired
    private UsersService usersService;

    @PostMapping()
    public ResponseEntity<Map> save(@RequestBody User user) {
        return new ResponseEntity<Map>(usersService.save(user), HttpStatus.OK);
    }

    @PutMapping(value = "/{user_id}")
    public ResponseEntity<Map> update(@PathVariable("user_id") Long id, @RequestBody User user) {
        return new ResponseEntity<Map>(usersService.update(user), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{user_id}"})
    public ResponseEntity<Map> delete(@PathVariable("user_id") Long id) throws Exception {
        return new ResponseEntity<Map>(usersService.delete(id), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long userId) throws Exception {
        return new ResponseEntity<Map>(usersService.getById(userId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Map> listSupplier(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String orderby,
            @RequestParam(required = false) String ordertype) {
        Pageable show_data = simpleStringUtils.getShort(orderby, ordertype, page, size);
        Page<User> list = null;
//        if(nama != null && !nama.isEmpty() && hp != null && !hp.isEmpty() ){
//            list = usersRepo.findByNamaLikeAndHpLike("%"+nama+"%","%"+hp+"%",show_data);
//        }else if(hp != null && !hp.isEmpty()){
//            list = usersRepo.findByHpLike("%"+hp+"%",show_data);
//        }else if(nama != null && !nama.isEmpty()){
//            list = usersRepo.findByNamaLike("%"+nama+"%",show_data);
//        }else {
//            // nampilkan semuanya
//        }
        list = usersRepository.getListData(show_data);

        return new ResponseEntity<Map>(response.sukses(list), new HttpHeaders(), HttpStatus.OK);
    }

}
