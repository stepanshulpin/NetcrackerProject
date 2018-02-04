package com.shulpin.server.service;


import com.shulpin.server.dao.UserInfoDao;
import com.shulpin.shared.model.Selector;
import com.shulpin.shared.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userInfoService")
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoDao dao;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        dao.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfoByName(String userName) {
        return dao.getUserInfoByName(userName);
    }

    @Override
    public List<UserInfo> findAllUsersWithoutUsername(String username, Selector selector) {
        return dao.findAllUsersWithoutUsername(username, selector);
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return dao.getUserInfoById(id);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        dao.updateUserInfo(userInfo);
    }

    @Override
    public UserInfo whereNotImage() {
        return dao.whereNotImage();
    }

    @Override
    public List<String> getCities() {
        return dao.getCities();
    }
}
