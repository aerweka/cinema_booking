package com.example.spring.service.impl;

import com.example.spring.entity.Book;
import com.example.spring.repository.BooksRepository;
import com.example.spring.service.BooksService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Service
public class BooksServiceImpl implements BooksService {
    private static Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    public Response response;

    @Override
    public Map save(Book request) {
        try {
            if (request.getFilmCode() == null) {
                return response.error("Mohon pilih film yang akan dipesan.", Config.ERROR_401);
            }

            Book doSave = booksRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Book request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            Book chekData = booksRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getFilmCode() == null) {
                return response.error("Mohon pilih film yang akan dipesan.", Config.ERROR_401);
            }
            //do update
            chekData.setBookDate(LocalDate.now());
            chekData.setStudioCode(request.getStudioCode());
            chekData.setFilmCode(request.getFilmCode());

            //langsung update
            Book doSave = booksRepository.save(chekData);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror update: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map delete(Long id) {
        try {
            if (id == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }
//            1. chek data kedatabase
            Book chekData = booksRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Book saveDeleted = booksRepository.save(chekData);
            return response.sukses("sukses");
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror delete: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map getById(Long request) {
        try {
            //validasi
            if (request == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }
            //1. chek data kedatabase
            Book chekData = booksRepository.findById(request).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            // response
            return response.sukses(chekData);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror getById: " + e.getMessage(), Config.ERROR_500);
        }
    }


    /** versiku
     @Override public ModelResponse<List<Book>> get() throws Exception {
     List<Book> allBooks = (List<Book>) booksRepo.findAll();
     ModelResponse<List<Book>> res = new ModelResponse<>();
     res.setData(allBooks);
     return res;
     }

     @Override public ModelResponse<Book> getById(Long id) throws Exception {
     Book user = booksRepo.findById(id).orElseThrow(() -> new Exception("Book not found"));
     ModelResponse<Book> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Book> store(Map<String, Object> body) throws Exception {
     Book newBook = new Book();
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     newBook.setBookname(body.get("username").toString());
     newBook.setPassword(bcrypt.encode(body.get("password").toString()));
     newBook.setEmail(body.get("email").toString());

     ModelResponse<Book> res = new ModelResponse<>();
     res.setData(booksRepo.save(newBook));
     res.setMessage("Book created successfully");
     return res;
     }

     @Override public ModelResponse<Book> update(Long id, Map<String, Object> body) throws Exception {
     Book user = booksRepo.findById(id).orElseThrow(() -> new Exception("Book not found"));
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     if (body.containsKey("username")) user.setBookname(body.get("username").toString());
     if (body.containsKey("email")) user.setEmail(body.get("email").toString());

     if (body.containsKey("password"))
     if (bcrypt.matches(body.get("old_password").toString(), user.getPassword()))
     user.setPassword(bcrypt.encode(body.get("password").toString()));

     ModelResponse<Book> res = new ModelResponse<>();
     res.setData(booksRepo.save(user));
     res.setMessage("Book updated successfully");
     return res;
     }

     @Override public ModelResponse<Book> delete(Long id) throws Exception {
     Book user = booksRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found"));
     user.setDeleted_date(new Date());
     booksRepo.save(user);

     ModelResponse<Book> res = new ModelResponse<>();
     res.setMessage("Book deleted successfully");
     return res;
     }
     */
}
