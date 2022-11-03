package com.example.spring.service.impl;

import com.example.spring.entity.Film;
import com.example.spring.entity.Film;
import com.example.spring.model.ModelResponse;
import com.example.spring.repository.FilmsRepo;
import com.example.spring.service.FilmsService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class FilmsServiceImpl implements FilmsService {
    private static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private FilmsRepo filmsRepo;

    @Autowired
    public Response response;

    @Override
    public Map save(Film request) {
        try {
            if (request.getTitle() == null) {
                return response.error("Nama wajib diisi.", Config.ERROR_401);
            }

            Film doSave = filmsRepo.save(request);
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

            Film chekData = filmsRepo.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getTitle() == null) {
                return response.error("Nama wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setTitle(request.getTitle());
            chekData.setCode(request.getCode());
            chekData.setIsPlayed(request.getIsPlayed());

            //langsung update
            Film doSave = filmsRepo.save(chekData);
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
            Film chekData = filmsRepo.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Film saveDeleted = filmsRepo.save(chekData);
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
            Film chekData = filmsRepo.findById(request).orElseThrow(RuntimeException::new);
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

    /**
     @Override public ModelResponse<List<Film>> get() throws Exception {
     List<Film> allUsers = (List<Film>) filmsRepo.findAll();
     ModelResponse<List<Film>> res = new ModelResponse<>();
     res.setData(allUsers);
     return res;
     }

     @Override public ModelResponse<Film> getById(Long id) throws Exception {
     Film user = filmsRepo.findById(id).orElseThrow(() -> new Exception("Film not found"));
     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Film> store(Map<String, Object> body) throws Exception {
     Film newFilm = new Film();

     newFilm.setCode(body.get("code").toString());
     newFilm.setTitle(body.get("title").toString());
     newFilm.setIsPlayed(Boolean.parseBoolean(body.get("email").toString()));

     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(filmsRepo.save(newFilm));
     res.setMessage("Film created successfully");
     return res;
     }

     @Override public ModelResponse<Film> update(Long id, Map<String, Object> body) throws Exception {
     Film film = filmsRepo.findById(id).orElseThrow(() -> new Exception("Film not found"));

     if (body.containsKey("code")) film.setCode(body.get("code").toString());
     if (body.containsKey("email")) film.setTitle(body.get("email").toString());
     if (body.containsKey("is_played")) film.setIsPlayed(Boolean.parseBoolean(body.get("is_played").toString()));

     ModelResponse<Film> res = new ModelResponse<>();
     res.setData(filmsRepo.save(film));
     res.setMessage("Film updated successfully");
     return res;
     }

     @Override public ModelResponse<Film> delete(Long id) throws Exception {
     Film user = filmsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Film not found"));
     filmsRepo.delete(user);

     ModelResponse<Film> res = new ModelResponse<>();
     res.setMessage("Film deleted successfully");
     return res;
     }
     */
}
