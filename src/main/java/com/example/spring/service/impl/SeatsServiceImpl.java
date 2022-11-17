package com.example.spring.service.impl;

import com.example.spring.entity.Seat;
import com.example.spring.repository.SeatsRepository;
import com.example.spring.service.SeatsService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SeatsServiceImpl implements SeatsService {
    private static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private SeatsRepository seatsRepository;

    @Autowired
    public Response response;

    @Override
    public Map save(Seat request) {
        try {
            if (request.getStudioCode() == null) {
                return response.error("Nama wajib diisi.", Config.ERROR_401);
            }

            Seat doSave = seatsRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Seat request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            Seat chekData = seatsRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getStudioCode() == null) {
                return response.error("Mohon pilih studio.", Config.ERROR_401);
            }
            //do update
            chekData.setStudioCode(request.getStudioCode());
            chekData.setBookId(request.getBookId());

            //langsung update
            Seat doSave = seatsRepository.save(chekData);
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
            Seat chekData = seatsRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Seat saveDeleted = seatsRepository.save(chekData);
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
            Seat chekData = seatsRepository.findById(request).orElseThrow(RuntimeException::new);
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
     @Override public ModelResponse<List<Seat>> get() throws Exception {
     List<Seat> allUsers = (List<Seat>) filmsRepo.findAll();
     ModelResponse<List<Seat>> res = new ModelResponse<>();
     res.setData(allUsers);
     return res;
     }

     @Override public ModelResponse<Seat> getById(Long id) throws Exception {
     Seat user = filmsRepo.findById(id).orElseThrow(() -> new Exception("Seat not found"));
     ModelResponse<Seat> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Seat> store(Map<String, Object> body) throws Exception {
     Seat newSeat = new Seat();

     newSeat.setCode(body.get("code").toString());
     newSeat.setTitle(body.get("title").toString());
     newSeat.setIsPlayed(Boolean.parseBoolean(body.get("email").toString()));

     ModelResponse<Seat> res = new ModelResponse<>();
     res.setData(filmsRepo.save(newSeat));
     res.setMessage("Seat created successfully");
     return res;
     }

     @Override public ModelResponse<Seat> update(Long id, Map<String, Object> body) throws Exception {
     Seat film = filmsRepo.findById(id).orElseThrow(() -> new Exception("Seat not found"));

     if (body.containsKey("code")) film.setCode(body.get("code").toString());
     if (body.containsKey("email")) film.setTitle(body.get("email").toString());
     if (body.containsKey("is_played")) film.setIsPlayed(Boolean.parseBoolean(body.get("is_played").toString()));

     ModelResponse<Seat> res = new ModelResponse<>();
     res.setData(filmsRepo.save(film));
     res.setMessage("Seat updated successfully");
     return res;
     }

     @Override public ModelResponse<Seat> delete(Long id) throws Exception {
     Seat user = filmsRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Seat not found"));
     filmsRepo.delete(user);

     ModelResponse<Seat> res = new ModelResponse<>();
     res.setMessage("Seat deleted successfully");
     return res;
     }
     */
}
