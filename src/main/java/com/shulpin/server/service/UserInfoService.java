package com.shulpin.server.service;

import com.shulpin.shared.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    void saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByName(String userName);

    List<UserInfo> findAllUsersWithoutUsername(String username);

    UserInfo getUserInfoById(Long id);

    void updateUserInfo(UserInfo userInfo);

    UserInfo whereNotImage();

}
