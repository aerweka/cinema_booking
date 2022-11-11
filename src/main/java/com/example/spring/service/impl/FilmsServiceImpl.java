package com.example.spring.service.impl;

import com.example.spring.entity.Film;
import com.example.spring.repository.FilmsRepository;
import com.example.spring.service.FilmsService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class FilmsServiceImpl implements FilmsService {
    private static Logger logger = LoggerFactory.getLogger(FilmsServiceImpl.class);
    @Autowired
    private FilmsRepository filmsRepository;

    @Autowired
    public Response response;

    @Override
    public Map save(Film request) {
        try {
            if (request.getTitle() == null) {
                return response.error("Judul film wajib diisi.", Config.ERROR_401);
            }

            Film doSave = filmsRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Film request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            Film chekData = filmsRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getTitle() == null) {
                return response.error("Judul film wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setTitle(request.getTitle());
            chekData.setIsPlayed(request.getIsPlayed());
            chekData.setCode(request.getCode());

            //langsung update
            Film doSave = filmsRepository.save(chekData);
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
            Film chekData = filmsRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Film saveDeleted = filmsRepository.save(chekData);
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
            Film chekData = filmsRepository.findById(request).orElseThrow(RuntimeException::new);
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
     @Override public ModelResponse<List<Film>> get() throws Exception {
     List<Film> allFilms = (List<Film>) studiosRepo.findAll();
     ModelResponse<List<Film>> res = new ModelResponse<>();
     res.setData(allFilms);
     return res;
     }

     @Override public ModelResponse<Film> getById(Long id) throws Exception {
     Film user = studiosRepo.findById(id).orElseThrow(() -> new Exception("Film not found"));
     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Film> store(Map<String, Object> body) throws Exception {
     Film newFilm = new Film();
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     newFilm.setFilmname(body.get("username").toString());
     newFilm.setPassword(bcrypt.encode(body.get("password").toString()));
     newFilm.setEmail(body.get("email").toString());

     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(studiosRepo.save(newFilm));
     res.setMessage("Film created successfully");
     return res;
     }

     @Override public ModelResponse<Film> update(Long id, Map<String, Object> body) throws Exception {
     Film user = studiosRepo.findById(id).orElseThrow(() -> new Exception("Film not found"));
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     if (body.containsKey("username")) user.setFilmname(body.get("username").toString());
     if (body.containsKey("email")) user.setEmail(body.get("email").toString());

     if (body.containsKey("password"))
     if (bcrypt.matches(body.get("old_password").toString(), user.getPassword()))
     user.setPassword(bcrypt.encode(body.get("password").toString()));

     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(studiosRepo.save(user));
     res.setMessage("Film updated successfully");
     return res;
     }

     @Override public ModelResponse<Film> delete(Long id) throws Exception {
     Film user = studiosRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Film not found"));
     user.setDeleted_date(new Date());
     studiosRepo.save(user);

     ModelResponse<Film> res = new ModelResponse<>();
     res.setMessage("Film deleted successfully");
     return res;
     }
     */
}
