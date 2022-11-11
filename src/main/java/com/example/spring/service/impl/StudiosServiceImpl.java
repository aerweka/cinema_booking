package com.example.spring.service.impl;

import com.example.spring.entity.Studio;
import com.example.spring.repository.StudiosRepository;
import com.example.spring.service.StudiosService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class StudiosServiceImpl implements StudiosService {
    private static Logger logger = LoggerFactory.getLogger(StudiosServiceImpl.class);
    @Autowired
    private StudiosRepository studiosRepository;

    @Autowired
    public Response response;

    @Override
    public Map save(Studio request) {
        try {
            if (request.getCode() == null) {
                return response.error("Code wajib diisi.", Config.ERROR_401);
            }

            Studio doSave = studiosRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(Studio request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            Studio chekData = studiosRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getCode() == null) {
                return response.error("Code wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setCode(request.getCode());
            chekData.setCapacity(request.getCapacity());

            //langsung update
            Studio doSave = studiosRepository.save(chekData);
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
            Studio chekData = studiosRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            Studio saveDeleted = studiosRepository.save(chekData);
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
            Studio chekData = studiosRepository.findById(request).orElseThrow(RuntimeException::new);
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
     @Override public ModelResponse<List<Studio>> get() throws Exception {
     List<Studio> allStudios = (List<Studio>) studiosRepo.findAll();
     ModelResponse<List<Studio>> res = new ModelResponse<>();
     res.setData(allStudios);
     return res;
     }

     @Override public ModelResponse<Studio> getById(Long id) throws Exception {
     Studio user = studiosRepo.findById(id).orElseThrow(() -> new Exception("Studio not found"));
     ModelResponse<Studio> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<Studio> store(Map<String, Object> body) throws Exception {
     Studio newStudio = new Studio();
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     newStudio.setStudioname(body.get("username").toString());
     newStudio.setPassword(bcrypt.encode(body.get("password").toString()));
     newStudio.setEmail(body.get("email").toString());

     ModelResponse<Studio> res = new ModelResponse<>();
     res.setData(studiosRepo.save(newStudio));
     res.setMessage("Studio created successfully");
     return res;
     }

     @Override public ModelResponse<Studio> update(Long id, Map<String, Object> body) throws Exception {
     Studio user = studiosRepo.findById(id).orElseThrow(() -> new Exception("Studio not found"));
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     if (body.containsKey("username")) user.setStudioname(body.get("username").toString());
     if (body.containsKey("email")) user.setEmail(body.get("email").toString());

     if (body.containsKey("password"))
     if (bcrypt.matches(body.get("old_password").toString(), user.getPassword()))
     user.setPassword(bcrypt.encode(body.get("password").toString()));

     ModelResponse<Studio> res = new ModelResponse<>();
     res.setData(studiosRepo.save(user));
     res.setMessage("Studio updated successfully");
     return res;
     }

     @Override public ModelResponse<Studio> delete(Long id) throws Exception {
     Studio user = studiosRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Studio not found"));
     user.setDeleted_date(new Date());
     studiosRepo.save(user);

     ModelResponse<Studio> res = new ModelResponse<>();
     res.setMessage("Studio deleted successfully");
     return res;
     }
     */
}
