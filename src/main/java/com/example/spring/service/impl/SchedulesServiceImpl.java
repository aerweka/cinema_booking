package com.example.spring.service.impl;

import com.example.spring.entity.Schedule;
import com.example.spring.repository.SchedulesRepository;
import com.example.spring.service.SchedulesService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SchedulesServiceImpl implements SchedulesService {
    private static Logger logger = LoggerFactory.getLogger(SchedulesServiceImpl.class);
    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    public Response response;

    @Override
    public Map save(Schedule request) {
        try {
            if (request.getShowDate() == null) {
                return response.error("Tanggal show wajib diisi.", Config.ERROR_401);
            }

            Schedule doSave = schedulesRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Schedule request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            Schedule chekData = schedulesRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getShowDate() == null) {
                return response.error("Tanggal show wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setShowDate(request.getShowDate());
            chekData.setFilmCode(request.getFilmCode());
            chekData.setShowStart(request.getShowStart());
            chekData.setShowEnd(request.getShowEnd());
            chekData.setTicketPrice(request.getTicketPrice());

            //langsung update
            Schedule doSave = schedulesRepository.save(chekData);
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
            Schedule chekData = schedulesRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Schedule saveDeleted = schedulesRepository.save(chekData);
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
            Schedule chekData = schedulesRepository.findById(request).orElseThrow(RuntimeException::new);
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
     @Override public ModelResponse<List<Schedule>> get() throws Exception {
     List<Schedule> allSchedules = (List<Schedule>) booksRepo.findAll();
     ModelResponse<List<Schedule>> res = new ModelResponse<>();
     res.setData(allSchedules);
     return res;
     }

     @Override public ModelResponse<Schedule> getById(Long id) throws Exception {
     Schedule user = booksRepo.findById(id).orElseThrow(() -> new Exception("Schedule not found"));
     ModelResponse<Schedule> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Schedule> store(Map<String, Object> body) throws Exception {
     Schedule newSchedule = new Schedule();
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     newSchedule.setSchedulename(body.get("username").toString());
     newSchedule.setPassword(bcrypt.encode(body.get("password").toString()));
     newSchedule.setEmail(body.get("email").toString());

     ModelResponse<Schedule> res = new ModelResponse<>();
     res.setData(booksRepo.save(newSchedule));
     res.setMessage("Schedule created successfully");
     return res;
     }

     @Override public ModelResponse<Schedule> update(Long id, Map<String, Object> body) throws Exception {
     Schedule user = booksRepo.findById(id).orElseThrow(() -> new Exception("Schedule not found"));
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     if (body.containsKey("username")) user.setSchedulename(body.get("username").toString());
     if (body.containsKey("email")) user.setEmail(body.get("email").toString());

     if (body.containsKey("password"))
     if (bcrypt.matches(body.get("old_password").toString(), user.getPassword()))
     user.setPassword(bcrypt.encode(body.get("password").toString()));

     ModelResponse<Schedule> res = new ModelResponse<>();
     res.setData(booksRepo.save(user));
     res.setMessage("Schedule updated successfully");
     return res;
     }

     @Override public ModelResponse<Schedule> delete(Long id) throws Exception {
     Schedule user = booksRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Schedule not found"));
     user.setDeleted_date(new Date());
     booksRepo.save(user);

     ModelResponse<Schedule> res = new ModelResponse<>();
     res.setMessage("Schedule deleted successfully");
     return res;
     }
     */
}
