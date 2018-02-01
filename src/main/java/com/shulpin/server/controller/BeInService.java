package com.shulpin.server.controller;


import com.shulpin.server.service.MessageService;
import com.shulpin.server.service.UserInfoService;
import com.shulpin.server.service.UserService;
import com.shulpin.shared.model.Message;
import com.shulpin.shared.model.MyResponse;
import com.shulpin.shared.model.User;
import com.shulpin.shared.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Controller
public class BeInService {

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    MessageService messageService;

    @GetMapping("/test")
    public ResponseEntity test(){
        return new ResponseEntity(HttpStatus.OK);
    }

    //при логине проверка пользователя на наличие и правильность пароля
    @PostMapping("/user")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<MyResponse> findUser(@RequestBody User user){
        User userFromBd=userService.findByUserName(user.getUserName());
        if(userFromBd==null){
            return new ResponseEntity<MyResponse>(new MyResponse("Not found"), HttpStatus.OK);
        }
        else{
            /*PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if(passwordEncoder.matches(user.getUserPassword(),userFromBd.getUserPassword())){
                return new ResponseEntity<MyResponse>(new MyResponse("Correct password"), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MyResponse>(new MyResponse("Wrong password"), HttpStatus.OK);
            }*/
            if(user.getUserPassword().equals(userFromBd.getUserPassword())){
                return new ResponseEntity<MyResponse>(new MyResponse("Correct password"), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MyResponse>(new MyResponse("Wrong password"), HttpStatus.OK);
            }

        }

    }

    @PostMapping("/saveUser")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<MyResponse> saveUser(@RequestBody User user){

        userService.saveUser(user);
        return new ResponseEntity<>(new MyResponse("Save user completed"), HttpStatus.OK);

    }

    @PostMapping("/saveUserInfo")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<MyResponse> saveUserInfo(@RequestBody UserInfo userInfo){

        userInfoService.saveUserInfo(userInfo);
        return new ResponseEntity<MyResponse>(new MyResponse("Save userInfo completed"), HttpStatus.OK);

    }

    @GetMapping("/users/{username}")
    @Transactional
    public ResponseEntity<List<UserInfo>> getAllUsersWithoutUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(userInfoService.findAllUsersWithoutUsername(username), HttpStatus.OK);
    }

    @PostMapping("/userInfo/{username}")
    @Transactional
    public ResponseEntity<Long> findUserInfoId(@PathVariable("username") String username){
        return new ResponseEntity<>(userInfoService.getUserInfoByName(username).getId(), HttpStatus.OK);
    }

    @PostMapping("/addMessage")
    @Transactional
    public ResponseEntity<MyResponse> addMessage(@RequestBody Message message){
        messageService.save(message);
        return new ResponseEntity<>(new MyResponse("Save complete!"), HttpStatus.OK);
    }

    @GetMapping("/messagesFrom/{id}")
    @Transactional
    public ResponseEntity<List<Message>> getMessagesFrom(@PathVariable("id") Long id){
        return new ResponseEntity<>(messageService.getAllFrom(id), HttpStatus.OK);
    }

    @GetMapping("/messagesTo/{id}")
    @Transactional
    public ResponseEntity<List<Message>> getMessagesTo(@PathVariable("id") Long id){
        return new ResponseEntity<>(messageService.getAllTo(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteMessage")
    @Transactional
    public ResponseEntity<MyResponse> deleteMessage(@RequestBody Message message){
        messageService.delete(message);
        return new ResponseEntity<>(new MyResponse("Delete completed!"),HttpStatus.OK);
    }

    @GetMapping("/userInfo/{id}")
    @Transactional
    public ResponseEntity<UserInfo> findUserInfoById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userInfoService.getUserInfoById(id), HttpStatus.OK);
    }

    @PostMapping("/loadFile")
    @Transactional
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ResponseEntity loadFile(@RequestParam("file") MultipartFile uploadfile){
        if (uploadfile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
        try {
            UserInfo userInfo = userInfoService.whereNotImage();
            userInfo.setImage(uploadfile.getBytes());
            userInfoService.updateUserInfo(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity("Successfully uploaded - ", HttpStatus.OK);
    }

}
