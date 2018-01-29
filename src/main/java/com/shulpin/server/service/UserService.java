package com.shulpin.server.service;

import com.shulpin.shared.model.User;

public interface UserService {

    void saveUser(User user);

    User findByUserName(String userName);

}
