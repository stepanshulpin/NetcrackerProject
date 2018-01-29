package com.shulpin.server.dao;

import com.shulpin.shared.model.User;

public interface UserDao {

    void saveUser(User user);

    User findByUserName(String userName);

}
