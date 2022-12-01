package com.example.spring.service.impl;

import com.example.spring.entity.Role;
import com.example.spring.entity.User;
import com.example.spring.model.Register;
import com.example.spring.repository.RoleRepository;
import com.example.spring.repository.UsersRepository;
import com.example.spring.service.UsersService;
import com.example.spring.utils.Config;
import com.example.spring.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    private static Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public Response response;

    Config config = new Config();

    @Autowired
    private PasswordEncoder encoder;

//    @Autowired
//    public TemplateResponse templateResponse;

    @Override
    public Map save(User request) {
        try {
            if (request.getUsername() == null) {
                return response.error("Nama wajib diisi.", Config.ERROR_401);
            }

            User doSave = usersRepository.save(request);
            return response.sukses(doSave);
        } catch (Exception e) {
            logger.error("Eror save,{} " + e);
            return response.error("eror save: " + e.getMessage(), Config.ERROR_500);
        }
    }

    @Override
    public Map update(User request) {
        try {
            if (request.getId() == null) {
                return response.error("Id wajib diisi.", Config.ERROR_401);
            }

            User chekData = usersRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }

            if (request.getUsername() == null) {
                return response.error("Nama wajib diisi.", Config.ERROR_401);
            }
            //do update
            chekData.setUsername(request.getUsername());
            chekData.setEmail(request.getEmail());
            chekData.setPassword(request.getPassword());

            //langsung update
            User doSave = usersRepository.save(chekData);
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
            User chekData = usersRepository.findById(id).orElseThrow(RuntimeException::new);
            if (chekData == null) {
                return response.error("Data tidak ditemukan", Config.ERROR_404);
            }
            //pengecekakn ke tabel barang : jika barang nya status active: ga bisa di delete: nanti

            //soft delete: 2.do delete
            chekData.setDeleted_date(new Date());
            User saveDeleted = usersRepository.save(chekData);
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
            User chekData = usersRepository.findById(request).orElseThrow(RuntimeException::new);
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

    @Override
    public Map registerManual(Register registerModel) {
        Map map = new HashMap();
        try {
            String[] roleNames = {"ROLE_USER", "ROLE_READ", "ROLE_WRITE"}; // admin
            User user = new User();
            user.setEmail(registerModel.getUsername().toLowerCase());
            user.setUsername(registerModel.getUsername().toLowerCase());
            user.setFullname(registerModel.getFullname());

            //step 1 :
//            user.setEnabled(false); // matikan user

            String password = encoder.encode(registerModel.getPassword().replaceAll("\\s+", ""));
            List<Role> r = roleRepository.findByNameIn(roleNames);

            user.setRoles(r);
            user.setPassword(password);
            User obj = usersRepository.save(user);

            return response.templateSukses(obj);

        } catch (Exception e) {
            logger.error("Eror registerManual=", e);
            return response.templateEror("eror:"+e);
        }

    }


    /** versiku
     @Override public ModelResponse<List<User>> get() throws Exception {
     List<User> allUsers = (List<User>) usersRepo.findAll();
     ModelResponse<List<User>> res = new ModelResponse<>();
     res.setData(allUsers);
     return res;
     }

     @Override public ModelResponse<User> getById(Long id) throws Exception {
     User user = usersRepo.findById(id).orElseThrow(() -> new Exception("User not found"));
     ModelResponse<User> res = new ModelResponse<>();
     res.setData(user);
     return res;
     }

     @Override public ModelResponse<User> store(Map<String, Object> body) throws Exception {
     User newUser = new User();
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     newUser.setUsername(body.get("username").toString());
     newUser.setPassword(bcrypt.encode(body.get("password").toString()));
     newUser.setEmail(body.get("email").toString());

     ModelResponse<User> res = new ModelResponse<>();
     res.setData(usersRepo.save(newUser));
     res.setMessage("User created successfully");
     return res;
     }

     @Override public ModelResponse<User> update(Long id, Map<String, Object> body) throws Exception {
     User user = usersRepo.findById(id).orElseThrow(() -> new Exception("User not found"));
     BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

     if (body.containsKey("username")) user.setUsername(body.get("username").toString());
     if (body.containsKey("email")) user.setEmail(body.get("email").toString());

     if (body.containsKey("password"))
     if (bcrypt.matches(body.get("old_password").toString(), user.getPassword()))
     user.setPassword(bcrypt.encode(body.get("password").toString()));

     ModelResponse<User> res = new ModelResponse<>();
     res.setData(usersRepo.save(user));
     res.setMessage("User updated successfully");
     return res;
     }

     @Override public ModelResponse<User> delete(Long id) throws Exception {
     User user = usersRepo.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
     user.setDeleted_date(new Date());
     usersRepo.save(user);

     ModelResponse<User> res = new ModelResponse<>();
     res.setMessage("User deleted successfully");
     return res;
     }
     */
}
