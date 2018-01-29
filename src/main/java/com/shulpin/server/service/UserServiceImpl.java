package com.shulpin.server.service;

import com.shulpin.server.dao.UserDao;
import com.shulpin.shared.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;


    @Override
    public void saveUser(User user) {
        //PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        dao.saveUser(user);
    }




    @Override
    public User findByUserName(String userName) {

        return dao.findByUserName(userName);

    }
}
